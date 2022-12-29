package org.dreambot.quests.freequests.doricsquest;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedDoricsQuestLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DORICS_QUEST.getId()) > 10;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Doric's Quest, stopping script...!");
        return -1;
    }
}
