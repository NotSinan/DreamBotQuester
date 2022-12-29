package org.dreambot.quests.piratestreasure.smugglerum.chatmsg;

import org.dreambot.framework.Leaf;
import org.dreambot.quests.piratestreasure.smugglerum.SmuggleState;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeStashedRumLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return API.lastGameMessage.contains("There is also some rum stashed in here too.") ||
                API.lastGameMessage.contains("There's already some rum in here...");
    }

    @Override
    public int onLoop() {
        SmuggleState.stashedRum = true;
        API.lastGameMessage = "";
        return Timing.getSleepDelay();
    }
}
