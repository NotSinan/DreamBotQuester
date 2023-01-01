package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;


import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.quest.book.MiniQuest;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class AlfredGrimhandsBarcrawl extends Branch {
    @Override
    public boolean isValid() { return CheckRequirements.checkRequirements(MiniQuest.ALFRED_GRIMHANDS_BARCRAWL); }
}
