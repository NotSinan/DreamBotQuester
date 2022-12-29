package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class GoblinDiplomacy extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.GOBLIN_DIPLOMACY.hasRequirements() && !FreeQuest.GOBLIN_DIPLOMACY.isFinished();
    }
}
