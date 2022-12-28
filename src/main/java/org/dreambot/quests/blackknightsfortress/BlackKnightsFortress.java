package org.dreambot.quests.blackknightsfortress;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class BlackKnightsFortress extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.BLACK_KNIGHTS_FORTRESS.hasRequirements() && !FreeQuest.BLACK_KNIGHTS_FORTRESS.isFinished();
    }
}
