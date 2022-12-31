package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "Sinan", questName = "Black Knight's Fortress")

public class BlackKnightsFortress extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(FreeQuest.BLACK_KNIGHTS_FORTRESS);
    }
}
