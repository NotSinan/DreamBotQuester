package org.dreambot.quests.freequests.sheepshearer;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "Sinan", questName = "Sheep shearer")
public class SheepShearer extends Branch {

    @Override
    public boolean isValid() {
        return FreeQuest.SHEEP_SHEARER.hasRequirements() && !FreeQuest.SHEEP_SHEARER.isFinished();
    }
}
