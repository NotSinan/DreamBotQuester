package org.dreambot.utilities.loadouts;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.utilities.Timing;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class InventoryLoadout {
    private LoadoutItem[] items;

    public InventoryLoadout(LoadoutItem... items) {
        this.items = items;
    }
    public List<LoadoutItem> getExtraItems() {
        List<LoadoutItem> extraItems = new ArrayList<>();
        List<LoadoutItem> inventoryItems = new ArrayList<>();

        for (Item i : Inventory.all()) {
            inventoryItems.add(new LoadoutItem(i.getName(), i.getAmount(), i.isNoted()));
        }

        for (LoadoutItem item : items) {
            int extra = 0;
            for (LoadoutItem invyItem : inventoryItems) {
                if (invyItem.isNoted() == item.isNoted() && invyItem.getItemName().equals(item.getItemName())) {
                    extra = invyItem.getItemQty();
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

    public void fulfill() {
        if (!Bank.isOpen()) {
            return;
        }

        Instant end = Instant.now().plusSeconds(120);
        while (end.isAfter(Instant.now()) && ScriptManager.getScriptManager().isRunning() && !ScriptManager.getScriptManager().isPaused()) {
            //diff check of LoadoutItems parameter and given Inventory before depositing all - fuck you camal
            List<LoadoutItem> extraItems = getExtraItems();
            if (!extraItems.isEmpty()) {
                Timing.sleepForDelay();
                if (Bank.depositAllItems()) {
                    extraItems.stream().forEach(i -> Logger.log("found extra item: " + i.getItemName() + " in qty: " + i.getItemQty() + ", noted: " + i.isNoted()));
                    Timing.sleepForTickDelay();
                }
                continue;
            }

            for (LoadoutItem item : items) {
                int currentQuantity = Inventory.count(item.getItemName());
                int neededQuantity = item.getItemQty() - currentQuantity;
                if (neededQuantity > 0) {
                    // Withdraw needed quantity from bank
                    Timing.sleepForDelay();
                    if (Bank.withdraw(item.getItemName(), neededQuantity)) {
                        Sleep.sleepUntil(() -> Inventory.count(item.getItemName()) == item.getItemQty(), 4000, 100);
                    }
                }
            }
        }
    }


}