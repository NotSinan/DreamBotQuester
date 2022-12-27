package org.dreambot.quests.clocktower;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.framework.Branch;

public class ClockTower extends Branch {
    @Override
    public boolean isValid() {
        return PaidQuest.CLOCK_TOWER.hasRequirements() && !PaidQuest.CLOCK_TOWER.isFinished();
    }
}
