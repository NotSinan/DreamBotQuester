package org.dreambot.quests.piratestreasure.smugglerum.chatmsg;

import org.dreambot.framework.Leaf;
import org.dreambot.quests.piratestreasure.smugglerum.SmuggleState;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeRumNoBananasLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return API.lastGameMessage.contains("There is some rum in here, although with no bananas to cover it. It is a little obvious.");
    }

    @Override
    public int onLoop() {
        SmuggleState.stashedRum = true;
        SmuggleState.filledCrateWithBananas = false;
        API.lastGameMessage = "";
        return Timing.getSleepDelay();
    }
}
