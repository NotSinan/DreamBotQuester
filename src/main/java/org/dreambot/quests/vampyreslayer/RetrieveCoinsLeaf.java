package org.dreambot.quests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankType;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveCoinsLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 &&
                !Inventory.contains("Coins") &&
                Inventory.contains("Garlic");
    }

    @Override
    public int onLoop() {
        if (Bank.open()) {
            Bank.withdraw("Coins", 100);
            Sleep.sleepUntil(() -> Inventory.count("Coins") == 100, 3000);
        }
        return Timing.loopReturn();
    }
}
