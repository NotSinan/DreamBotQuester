package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class XMarksTheSpot extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.X_MARKS_THE_SPOT.hasRequirements();
    }
}
