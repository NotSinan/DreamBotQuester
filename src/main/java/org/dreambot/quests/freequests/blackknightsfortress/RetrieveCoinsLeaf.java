package org.dreambot.quests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveCoinsLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_BLACK_KNIGHTS_FORTRESS.getId()) == 0 && Inventory.count("Coins") <= 300;
    }

    @Override
    public int onLoop() {
        return QuestHelper.withdrawFromBank("Coins", 1000);
    }
}
