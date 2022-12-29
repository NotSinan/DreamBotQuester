package org.dreambot.quests.freequests.impcatcher;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class ImpCatcher extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.IMP_CATCHER.hasRequirements();
    }
}
