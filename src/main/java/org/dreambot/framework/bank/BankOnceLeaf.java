package org.dreambot.framework.bank;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class BankOnceLeaf extends Leaf {

    private static boolean emptied = false;

    @Override
    public boolean isValid() {
        return !emptied;
    }

    @Override
    public int onLoop() {
        if(Inventory.isEmpty() && Equipment.isEmpty()) {
            emptied = true;
            return Timing.getSleepDelay();
        }

        Timing.sleepForDelay();
        if(Bank.open()) {
            if(!Inventory.isEmpty() && Bank.depositAllItems()) {
                Sleep.sleepUntil(Inventory::isEmpty, 3000);
            }

            if(!Equipment.isEmpty() && Bank.depositAllEquipment()) {
                Sleep.sleepUntil(Equipment::isEmpty, 3000);
            }
            return Timing.loopReturn();
        }
        return Timing.loopReturn();
    }
}
