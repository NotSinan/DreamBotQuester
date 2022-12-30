package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "Sinan", questName = "X marks the spot")
public class XMarksTheSpot extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.X_MARKS_THE_SPOT.hasRequirements() && !FreeQuest.X_MARKS_THE_SPOT.isFinished();
    }
}
