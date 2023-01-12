package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.BankHelper;

public class WithdrawFromBankPiratesTreasureLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Bank.isOpen();
    }

    @Override
    public int onLoop() {
        switch (PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID())) {
            case 0:
            case 1: {
                if (!Inventory.contains("Karamjan rum") && Bank.contains("Karamjan rum")) {
                    return BankHelper.withdrawFromBank("Karamjan rum", 1);
                }
                if (!Inventory.contains("Karamjan rum") && Inventory.count("Coins") < 1000 && Bank.contains("Coins")) {
                    return BankHelper.withdrawFromBank("Coins", 1000);
                }
                if (!Inventory.contains("Spade") && Bank.contains("Spade")) {
                    return BankHelper.withdrawFromBank("Spade", 1);
                }
                if (Inventory.count("Banana") < 10 && Bank.contains("Banana")) {
                    return BankHelper.withdrawFromBank("Banana", 10);
                }
                if (Inventory.count("White apron") < 10 && Bank.contains("White apron")) {
                    return BankHelper.withdrawFromBank("White apron", 1);
                }
                break;
            }
            case 2: {
                if (!Inventory.contains("Spade") && Bank.contains("Spade")) {
                    return BankHelper.withdrawFromBank("Spade", 1);
                }
                if (!Inventory.contains("Chest key") && Bank.contains("Chest key")) {
                    return BankHelper.withdrawFromBank("Chest key", 1);
                }
                break;
            }
            case 3: {
                if (!Inventory.contains("Spade") && Bank.contains("Spade")) {
                    return BankHelper.withdrawFromBank("Spade", 1);
                }
                break;
            }
        }
        if (Bank.close()) {
            Sleep.sleepUntil(() -> !Bank.isOpen(), 3000);
        }
        return Timing.loopReturn();
    }
}
