package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class EngageBoosterThrusters extends Leaf {

    @Override
    public boolean isValid() {
        return Players.getLocal().isInCombat() && Walking.getRunEnergy() >= 2 && !Walking.isRunEnabled();
    }

    @Override
    public int onLoop() {
        Timing.sleepForDelay();
        Walking.toggleRun();
        return Timing.loopReturn();
    }
}
