package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedVampyreSlayerLeaf extends Leaf {

    @Override
    public boolean isValid() { return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) >= 3; }

    @Override
    public int onLoop() {
        Logger.log("Finished Vampyre Slayer, stopping script...!");
        return -1;
    }
}
