package org.dreambot.quests.sheepshearer;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class SheepShearer extends Branch {

    @Override
    public boolean isValid() {
        return FreeQuest.SHEEP_SHEARER.hasRequirements();
    }
}
