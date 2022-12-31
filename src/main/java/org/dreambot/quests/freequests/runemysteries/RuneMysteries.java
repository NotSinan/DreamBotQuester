package org.dreambot.quests.freequests.runemysteries;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "Sinan", questName = "Rune mysteries")
public class RuneMysteries extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(FreeQuest.RUNE_MYSTERIES);
    }

}
