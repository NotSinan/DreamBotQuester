package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "420", questName = "The restless ghost")
public class TheRestlessGhost extends Branch {
    @Override
    public boolean isValid() {
        return FreeQuest.THE_RESTLESS_GHOST.hasRequirements() && !FreeQuest.THE_RESTLESS_GHOST.isFinished();
    }
}
