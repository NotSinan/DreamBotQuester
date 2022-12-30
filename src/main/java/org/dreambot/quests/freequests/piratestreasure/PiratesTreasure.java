package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "420", questName = "Pirate's treasure")
public class PiratesTreasure extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.PIRATES_TREASURE.hasRequirements() && !FreeQuest.PIRATES_TREASURE.isFinished();
    }
}
