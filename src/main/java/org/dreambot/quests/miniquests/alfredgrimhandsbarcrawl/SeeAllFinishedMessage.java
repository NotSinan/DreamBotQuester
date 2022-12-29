package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;

import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeAllFinishedMessage extends Leaf {

    @Override
    public boolean isValid() { return API.lastGameMessage.contains("You are too drunk to be able to read the barcrawl card."); }

    @Override
    public int onLoop() {
        CardState.finishedCard = true;
        API.lastGameMessage = "";
        return Timing.loopReturn();
    }
}
