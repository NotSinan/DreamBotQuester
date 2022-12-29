package org.dreambot.quests.freequests.runemysteries;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class RuneMysteries extends Branch {
    @Override
    public boolean isValid() { return FreeQuest.RUNE_MYSTERIES.hasRequirements(); }

}
