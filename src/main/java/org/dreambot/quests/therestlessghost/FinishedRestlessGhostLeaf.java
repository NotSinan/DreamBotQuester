package org.dreambot.quests.therestlessghost;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedRestlessGhostLeaf extends Leaf {

    @Override
    public boolean isValid() { return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) > 4; }

    @Override
    public int onLoop() {
        Logger.log("Finished The Restless Ghost, stopping script...!");
        return -1;
    }
}
