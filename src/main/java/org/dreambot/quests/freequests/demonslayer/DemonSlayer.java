package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class DemonSlayer extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(FreeQuest.DEMON_SLAYER);
    }
}
