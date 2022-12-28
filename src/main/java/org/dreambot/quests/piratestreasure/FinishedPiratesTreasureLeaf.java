package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedPiratesTreasureLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) > 3;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Pirate's Treasure, stopping script...!");
        return -1;
    }
}
