package org.dreambot.utilities.loadouts;

import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.wrappers.items.Item;

import java.util.ArrayList;
import java.util.List;

public class EquipmentLoadout {
    private LoadoutItem[] items;

    public EquipmentLoadout(LoadoutItem... items) {
        this.items = items;
    }

    public List<LoadoutItem> getExtraItems() {
        List<LoadoutItem> extraItems = new ArrayList<>();
        List<LoadoutItem> equipmentItems = new ArrayList<>();

        for (Item i : Equipment.all()) {
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
/*
    public void fulfill() {
        if (!Bank.isOpen()) {
            return;
        }

        Instant end = Instant.now().plusSeconds(120);
        while (end.isAfter(Instant.now()) && ScriptManager.getScriptManager().isRunning() && !ScriptManager.getScriptManager().isPaused()) {
            //diff check of LoadoutItems parameter and given Inventory before depositing all - fuck you camal
            List<LoadoutItem> extraEquipmentItems = getExtraItems();
            if (!extraEquipmentItems.isEmpty()) {
                Timing.sleepForDelay();
                if (Bank.depositAllEquipment()) {
                    extraEquipmentItems.stream().forEach(i -> Logger.log("found extra item: " + i.getItemName() + " in qty: " + i.getItemQty()));
                    Timing.sleepForTickDelay();
                }
                continue;
            }

            for (LoadoutItem item : items) {
                int currentEquipmentQuantity = Equipment.count(item.getItemName());
                int neededEquipmentQuantity = item.getItemQty() - currentEquipmentQuantity;
                if (neededEquipmentQuantity > 0) {
                    // Equip from inventory if have any
                    int currentInventoryQuantity = Inventory.count(item.getItemName());

                    Timing.sleepForDelay();
                    if (Bank.withdraw(item.getItemName(), neededQuantity) &&
                        !Sleep.sleepUntil(() -> Equipment.count(item.getItemName()) == item.getItemQty(), 4000, 100)) {
                        break;
                    }
                }
            }
        }
    }
*/

}