package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "Sinan", questName = "Vampyre slayer")
public class VampyreSlayer extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(FreeQuest.VAMPIRE_SLAYER);
    }
}
