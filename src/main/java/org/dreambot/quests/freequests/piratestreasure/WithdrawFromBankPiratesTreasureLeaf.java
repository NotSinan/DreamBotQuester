package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class WithdrawFromBankPiratesTreasureLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Bank.isOpen();
    }

    @Override
    public int onLoop() {
        switch (PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId())) {
            case 0:
            case 1: {
                if (!Inventory.contains("Karamjan rum") && Bank.contains("Karamjan rum")) {
                    return QuestHelper.withdrawFromBank("Karamjan rum", 1);
                }
                if (!Inventory.contains("Karamjan rum") && Inventory.count("Coins") < 1000 && Bank.contains("Coins")) {
                    return QuestHelper.withdrawFromBank("Coins", 1000);
                }
                if (!Inventory.contains("Spade") && Bank.contains("Spade")) {
                    return QuestHelper.withdrawFromBank("Spade", 1);
                }
                if (Inventory.count("Banana") < 10 && Bank.contains("Banana")) {
                    return QuestHelper.withdrawFromBank("Banana", 10);
                }
                if (Inventory.count("White apron") < 10 && Bank.contains("White apron")) {
                    return QuestHelper.withdrawFromBank("White apron", 1);
                }
                break;
            }
            case 2: {
                if (!Inventory.contains("Spade") && Bank.contains("Spade")) {
                    return QuestHelper.withdrawFromBank("Spade", 1);
                }
                if (!Inventory.contains("Chest key") && Bank.contains("Chest key")) {
                    return QuestHelper.withdrawFromBank("Chest key", 1);
                }
                break;
            }
            case 3: {
                if (!Inventory.contains("Spade") && Bank.contains("Spade")) {
                    return QuestHelper.withdrawFromBank("Spade", 1);
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
