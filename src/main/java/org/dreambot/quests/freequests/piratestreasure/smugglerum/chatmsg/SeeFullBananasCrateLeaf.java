package org.dreambot.quests.freequests.piratestreasure.smugglerum.chatmsg;

import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.piratestreasure.smugglerum.SmuggleState;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeFullBananasCrateLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return API.lastGameMessage.contains("The crate is full of bananas.") ||
                API.lastGameMessage.contains("The crate is already full.");
    }

    @Override
    public int onLoop() {
        SmuggleState.filledCrateWithBananas = true;
        API.lastGameMessage = "";
        return Timing.getSleepDelay();
    }
}
