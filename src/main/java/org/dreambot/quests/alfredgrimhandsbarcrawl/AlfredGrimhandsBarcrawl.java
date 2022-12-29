package org.dreambot.quests.alfredgrimhandsbarcrawl;


import org.dreambot.api.methods.quest.book.MiniQuest;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.framework.Branch;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class AlfredGrimhandsBarcrawl extends Branch {
    @Override
    public boolean isValid() { return MiniQuest.ALFRED_GRIMHANDS_BARCRAWL.hasRequirements(); }
}
