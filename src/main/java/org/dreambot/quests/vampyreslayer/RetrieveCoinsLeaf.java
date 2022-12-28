package org.dreambot.quests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.container.impl.bank.BankType;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveCoinsLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 &&
                Inventory.count("Coins") < 100 &&
                Inventory.contains("Garlic");
    }

    @Override
    public int onLoop() { return QuestHelper.withdrawFromBank("Coins", 100); }
}
