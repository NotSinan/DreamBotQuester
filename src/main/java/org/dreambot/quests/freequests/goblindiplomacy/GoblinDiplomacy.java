package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "Sinan", questName = "Goblin diplomacy")
public class GoblinDiplomacy extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.GOBLIN_DIPLOMACY.hasRequirements() && !FreeQuest.GOBLIN_DIPLOMACY.isFinished();
    }
}
