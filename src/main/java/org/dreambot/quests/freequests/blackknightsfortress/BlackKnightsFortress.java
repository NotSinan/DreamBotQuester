package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "Sinan", questName = "Black Knight's Fortress")

public class BlackKnightsFortress extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.BLACK_KNIGHTS_FORTRESS.hasRequirements() && !FreeQuest.BLACK_KNIGHTS_FORTRESS.isFinished();
    }
}
