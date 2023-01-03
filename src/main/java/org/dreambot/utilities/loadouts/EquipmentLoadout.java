package org.dreambot.utilities.loadouts;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.utilities.Interaction;
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

    public boolean fulfilledExact() {
        //check extranneous items2
        List<LoadoutItem> extraEquipmentItems = getExtraItems();
        if (!extraEquipmentItems.isEmpty()) {
            return false;
        }

        //check missing items
        for (LoadoutItem item : items) {
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
            //diff check of LoadoutItems parameter and given Equipment before depositing all - fuck you camal
            //step 1: empty equipment
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
                boolean wearingAllEquipment = true;
                while (end.isAfter(Instant.now()) && ScriptManager.getScriptManager().isRunning() && !ScriptManager.getScriptManager().isPaused()) {
                    for (LoadoutItem item : items) {
                        Item invyItem = Inventory.get(item.getItemName());
                        if (invyItem == null || !invyItem.isValid()) {
                            continue;
                        }
                        String[] actions = new String[]{"Wear","Wield","Equip"};
                        String action = Arrays.stream(invyItem.getActions())
                                .filter(a -> Arrays.stream(actions).anyMatch(i -> i.equals(a)))
                                .findFirst().orElse(null);
                        if (action != null && Interaction.delayInventoryInteract(invyItem.getName(), action)) {
                            wearingAllEquipment = false;
                            Timing.sleepForDelay();
                        }
                    }
                    if (wearingAllEquipment) {
                        Logger.log("Have no extra equipment and no equipment missing, equipment fulfilled!");
                        return true;
                    }
                }
            }
        }
        Logger.log("120s timeout for EquipmentLoadout.fulfill() occurred - failed to fulfill equipment");
        return false;
    }
}