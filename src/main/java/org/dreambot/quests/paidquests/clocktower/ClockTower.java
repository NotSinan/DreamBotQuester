package org.dreambot.quests.paidquests.clocktower;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class ClockTower extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(PaidQuest.CLOCK_TOWER);
    }
}
