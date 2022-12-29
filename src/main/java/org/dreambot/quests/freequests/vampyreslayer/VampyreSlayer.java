package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class VampyreSlayer extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.VAMPIRE_SLAYER.hasRequirements();
    }
}
