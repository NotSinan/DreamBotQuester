package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class RetrieveCoinsLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.BLACK_KNIGHTS_FORTRESS.getConfigID()) == 0 && Inventory.count("Coins") <= 300;
    }

    @Override
    public int onLoop() {
        return QuestHelper.withdrawFromBank("Coins", 1000);
    }
}
