package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "Sinan", questName = "Vampyre slayer")
public class VampyreSlayer extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.VAMPIRE_SLAYER.hasRequirements() && !FreeQuest.VAMPIRE_SLAYER.isFinished();
    }
}
