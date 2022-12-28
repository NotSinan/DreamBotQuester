package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.hint.HintArrow;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.piratestreasure.DigGardenLeaf;
import org.dreambot.quests.piratestreasure.smugglerum.SmuggleState;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeGardenerPresentLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return API.lastGameMessage.contains("I can't dig up anything with him attacking me!");
    }

    @Override
    public int onLoop() {
        if(HintArrow.exists()) {
            if(DigGardenLeaf.escapingGardener()) {
                API.lastGameMessage = "";
                return Timing.loopReturn();
            }

            if(!Sleep.sleepUntil(() -> !HintArrow.exists(), 15000)) {
                return Timing.loopReturn();
            }
        }
        API.lastGameMessage = "";
        return Timing.getSleepDelay();
    }
}
