package org.dreambot.utilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;

import java.util.Arrays;

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
        return Arrays.stream(itemNames).allMatch(itemName -> Bank.contains(itemName) || Inventory.contains(itemName) || Equipment.contains(itemName));
    }

}
