package org.dreambot.quests.witchspotion;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedWitchsPotionLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_WITCHS_POTION.getId()) > 2;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Witch's Potion! Stopping script...!");
        return -1;
    }
}
