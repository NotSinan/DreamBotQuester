package org.dreambot.quests.paidquests.monksfriend;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class MonksFriend extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(PaidQuest.MONKS_FRIEND);
    }
}
