package org.dreambot.quests.monksfriend;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.framework.Branch;

public class MonksFriend extends Branch {
    @Override
    public boolean isValid() {
        return PaidQuest.MONKS_FRIEND.hasRequirements() && !PaidQuest.MONKS_FRIEND.isFinished();
    }
}
