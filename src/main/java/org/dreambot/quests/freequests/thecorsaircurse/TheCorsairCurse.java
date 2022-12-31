package org.dreambot.quests.freequests.thecorsaircurse;


import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "420", questName = "Corsair curse")
public class TheCorsairCurse extends Branch {
    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(FreeQuest.CORSAIR_CURSE);
    }
}
