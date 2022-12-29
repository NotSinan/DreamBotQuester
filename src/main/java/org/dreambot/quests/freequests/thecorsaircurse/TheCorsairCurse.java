package org.dreambot.quests.freequests.thecorsaircurse;


import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;

public class TheCorsairCurse extends Branch {
    @Override
    public boolean isValid() { return FreeQuest.CORSAIR_CURSE.hasRequirements(); }
}
