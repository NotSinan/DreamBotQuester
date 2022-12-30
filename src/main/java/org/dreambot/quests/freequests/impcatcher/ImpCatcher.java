package org.dreambot.quests.freequests.impcatcher;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "Sinan", questName = "Imp catcher")
public class ImpCatcher extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.IMP_CATCHER.hasRequirements() && !FreeQuest.IMP_CATCHER.isFinished();
    }
}
