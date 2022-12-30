package org.dreambot.quests.freequests.romeoandjuliet;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "Sinan", questName = "Romeo and juliet")
public class RomeoAndJuliet extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.ROMEO_AND_JULIET.hasRequirements() && !FreeQuest.ROMEO_AND_JULIET.isFinished();
    }
}
