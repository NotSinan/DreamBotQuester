package org.dreambot.quests.paidquests.junglepotion;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.framework.Branch;

public class JunglePotion extends Branch {
    @Override
    public boolean isValid() {
        return PaidQuest.JUNGLE_POTION.hasRequirements() && !PaidQuest.JUNGLE_POTION.isFinished();
    }
}
