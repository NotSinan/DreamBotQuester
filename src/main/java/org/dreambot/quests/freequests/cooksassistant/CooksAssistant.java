package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "420", questName = "Cook's assistant")
public class CooksAssistant extends Branch {

    @Override
    public boolean isValid() {
        return FreeQuest.COOKS_ASSISTANT.hasRequirements() && !FreeQuest.COOKS_ASSISTANT.isFinished();
    }
}
