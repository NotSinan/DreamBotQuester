package org.dreambot.utilities.helpers;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.utilities.Timing;

public class BankHelper {
    public static int withdrawFromBank(String itemName, int quantity) {
        if(!Bank.isOpen()) {
            Timing.sleepForDelay();
            if (Bank.open()) {
                Timing.loopReturn();
            }
            return Timing.getSleepDelay();
        }

        if(Bank.count(itemName) <= 0) {
            Logger.log("Attempted to withdraw itemName / quantity: " + itemName + " / " + quantity + " - but have none in bank!");
            return Timing.loopReturn();
        }

        Timing.sleepForDelay();
        if(Bank.withdraw(itemName, quantity)) {
            Sleep.sleepUntil(() -> Inventory.count(itemName) >= quantity, 3000);
        }
        return Timing.loopReturn();
    }

}
