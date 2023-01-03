package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Branch;

public class SmuggleRumBranch extends Branch {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 1;
    }
}
