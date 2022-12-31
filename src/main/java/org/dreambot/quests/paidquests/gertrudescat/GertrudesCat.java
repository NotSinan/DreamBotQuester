package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class GertrudesCat extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(PaidQuest.GERTRUDES_CAT);
    }
}
