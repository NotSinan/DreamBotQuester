package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class DruidicRitual extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(PaidQuest.DRUIDIC_RITUAL);
    }
}
