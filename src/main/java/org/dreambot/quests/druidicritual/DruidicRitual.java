package org.dreambot.quests.druidicritual;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.framework.Branch;

public class DruidicRitual extends Branch {
    @Override
    public boolean isValid() {
        return PaidQuest.DRUIDIC_RITUAL.hasRequirements();
    }
}
