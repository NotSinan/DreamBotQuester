package org.dreambot.utilities.loadouts;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;

public class OwnedItems {
    public static int count(String itemName) {
        return Bank.count(itemName) + Inventory.count(itemName) + Equipment.count(itemName);
    }

    public static boolean contains(String itemName) {
        return Bank.contains(itemName) || Inventory.contains(itemName) || Equipment.contains(itemName);
    }

    public static boolean contains(String... itemNames) {
        return Bank.contains(itemNames) || Inventory.contains(itemNames) || Equipment.contains(itemNames);
    }

    public static boolean containsAll(String... itemNames) {
        for (String itemName : itemNames) {
            if (Bank.contains(itemName) || Inventory.contains(itemName) || Equipment.contains(itemName)) {
                continue;
            }
            return false;
        }
        return true;
    }

}
