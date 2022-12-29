package org.dreambot.quests.alfredgrimhandsbarcrawl;

import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.methods.quest.book.MiniQuest;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;

public class FinishedAlfredGrimhandsBarcrawlLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Quests.isFinished(MiniQuest.ALFRED_GRIMHANDS_BARCRAWL);
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Alfred Grimhand's Barcrawl, exiting script...!");
        return -1;
    }
}
