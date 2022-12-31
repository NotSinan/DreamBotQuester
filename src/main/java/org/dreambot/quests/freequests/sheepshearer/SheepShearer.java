package org.dreambot.quests.freequests.sheepshearer;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "Sinan", questName = "Sheep shearer")
public class SheepShearer extends Branch {

    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(FreeQuest.SHEEP_SHEARER);
    }
}
