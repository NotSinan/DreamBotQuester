package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;

public class FinishedCorsairCurseLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Quests.isFinished(FreeQuest.CORSAIR_CURSE);
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Corsair Curse, exiting script...!");
        return -1;
    }
}
