package org.dreambot.utilities.loadouts;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.OwnedItems;
import org.dreambot.utilities.Timing;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquipmentLoadout {
    private List<LoadoutItem> items;
    public EquipmentLoadout(LoadoutItem... loadoutItems) {
        this.items = new ArrayList<>();
        for (LoadoutItem i : loadoutItems) {
            this.items.add(i);
        }
    }

    public void addItem(LoadoutItem loadoutItem) {
        items.add(loadoutItem);
    }

    private List<LoadoutItem> getExtraItems() {
        List<LoadoutItem> extraItems = new ArrayList<>();
        List<LoadoutItem> equipmentItems = new ArrayList<>();

        for (Item i : Equipment.all()) {
            if (i == null || !i.isValid()) continue;
            equipmentItems.add(new LoadoutItem(i.getName(), i.getAmount()));
        }

        for (LoadoutItem item : items) {
            int extra = 0;
            for (LoadoutItem equippedItem : equipmentItems) {
                if (equippedItem.getItemName().equals(item.getItemName())) {
                    extra = equippedItem.getItemQty();
                    break;
                }
            }
            extra -= item.getItemQty();
            if (extra > 0) {
                extraItems.add(new LoadoutItem(item.getItemName(), extra));
            }
        }
        return extraItems;
    }

    public boolean fulfilled() {
        //check extranneous items
        List<LoadoutItem> extraEquipmentItems = getExtraItems();
        if (!extraEquipmentItems.isEmpty()) {
            Logger.log("Have extra items in Equipment:");
            extraEquipmentItems.forEach(l -> Logger.log("Name: "+l.getItemName()+", qty: "+l.getItemQty()));
            return false;
        }

        //check missing items
        for (LoadoutItem item : items) {
            if (!OwnedItems.contains(item.getItemName())) {
                Logger.log("Do not have item: " + item.getItemName() + " owned, skipping");
                continue;
            }
            int currentQuantity = Equipment.count(item.getItemName());
            int neededQuantity = item.getItemQty() - currentQuantity;
            if (neededQuantity > 0) {
                return false;
            }
        }

        //exactly fulfilled
        return true;
    }

    public boolean fulfill() {
        //90s to open nearest bank
        Instant end = Instant.now().plusSeconds(90);
        while (end.isAfter(Instant.now()) && ScriptManager.getScriptManager().isRunning() && !ScriptManager.getScriptManager().isPaused()) {
            if (!Bank.isOpen()) {
                Timing.sleepForDelay();
                Bank.open();
                Timing.sleepForTickDelay();
                continue;
            }
            break;
        }


        //90s to fulfill equipment while in bank
        end = Instant.now().plusSeconds(90);
        while (end.isAfter(Instant.now()) && ScriptManager.getScriptManager().isRunning() && !ScriptManager.getScriptManager().isPaused()) {
            //step 1: empty equipment
            // diff check of LoadoutItems parameter and given Equipment before depositing all - fuck you camal
            List<LoadoutItem> extraEquipmentItems = getExtraItems();
            if (!extraEquipmentItems.isEmpty()) {
                Timing.sleepForDelay();
                if (Bank.depositAllEquipment()) {
                    extraEquipmentItems.stream().forEach(i -> Logger.log("found extra Equipment item: " + i.getItemName() + " in qty: " + i.getItemQty()));
                    Timing.sleepForTickDelay();
                }
                continue;
            }
            //step 2: withdraw equipment as inventory loadout
            InventoryLoadout loadoutUnnoted = new InventoryLoadout();
            for (LoadoutItem loadoutItem : items) {
                loadoutUnnoted.addItem(new LoadoutItem(loadoutItem.getItemName(), loadoutItem.getItemQty()));
            }
            if(loadoutUnnoted.fulfill()) {
                //step 3: wear equipment
                while (end.isAfter(Instant.now()) && ScriptManager.getScriptManager().isRunning() && !ScriptManager.getScriptManager().isPaused()) {
                    boolean wearingAllEquipment = true;
                    for (LoadoutItem item : items) {
                        Item invyItem = Inventory.get(item.getItemName());
                        if (invyItem == null || !invyItem.isValid() || invyItem.getID() == -1 || invyItem.getName() == null || invyItem.getName().equalsIgnoreCase("null")) {
                            continue;
                        }
                        String[] actions = new String[]{"Wear","Wield","Equip"};
                        String action = Arrays.stream(invyItem.getActions())
                                .filter(a -> Arrays.stream(actions).anyMatch(i -> i.equals(a)))
                                .findFirst().orElse(null);
                        if (action != null) {
                            wearingAllEquipment = false;
                            Logger.log("Found equipment item, attempting to equip with action: "+ invyItem.getName() +", " + action);
                            if (Interaction.delayInventoryInteract(invyItem.getName(), action)) {
                                Sleep.sleepTick();
                                Timing.sleepForDelay();
                            }
                        }
                    }
                    if (wearingAllEquipment) {
                        Logger.log("Fulfilled equipment - have no extra equipment and no equipment left in inventory!");
                        return true;
                    }
                }
            }
        }
        Logger.log("120s timeout for EquipmentLoadout.fulfill() occurred - failed to fulfill equipment");
        return false;
    }
}