package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;

@QuestManifest(author = "420", questName = "Witch's potion")
public class WitchsPotion extends Branch {

    @Override
    public boolean isValid() {
        return FreeQuest.WITCHS_POTION.hasRequirements() && !FreeQuest.WITCHS_POTION.isFinished();
    }
}
