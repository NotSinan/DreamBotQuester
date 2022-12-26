package org.dreambot.quests.romeoandjuliet;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class RomeoAndJuliet extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.ROMEO_AND_JULIET.hasRequirements();
    }
}
