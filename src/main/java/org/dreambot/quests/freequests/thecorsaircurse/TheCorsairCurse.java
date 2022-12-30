package org.dreambot.quests.freequests.thecorsaircurse;


import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "420", questName = "Corsair curse")
public class TheCorsairCurse extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.CORSAIR_CURSE.hasRequirements() && !FreeQuest.CORSAIR_CURSE.isFinished();
    }
}
