package org.dreambot.quests.freequests.impcatcher;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "Sinan", questName = "Imp catcher")
public class ImpCatcher extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(FreeQuest.IMP_CATCHER);
    }
}
