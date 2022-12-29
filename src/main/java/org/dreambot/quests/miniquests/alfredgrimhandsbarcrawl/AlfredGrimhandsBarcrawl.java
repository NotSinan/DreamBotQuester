package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;


import org.dreambot.api.methods.quest.book.MiniQuest;
import org.dreambot.framework.Branch;

public class AlfredGrimhandsBarcrawl extends Branch {
    @Override
    public boolean isValid() { return MiniQuest.ALFRED_GRIMHANDS_BARCRAWL.hasRequirements(); }
}
