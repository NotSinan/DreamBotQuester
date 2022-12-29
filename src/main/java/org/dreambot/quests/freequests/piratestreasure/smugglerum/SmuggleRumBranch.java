package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestVarPlayer;

public class SmuggleRumBranch extends Branch {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 1;
    }
}
