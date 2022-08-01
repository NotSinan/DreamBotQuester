package org.dreambot.behaviour.timeout;

import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class TimeoutLeaf extends Leaf {

    // If this leaf is called, that means that there is currently a tick timeout
    @Override
    public boolean isValid() {
        return Timing.tickTimeout > 0;
    }

    @Override
    public int onLoop() {
        // Decrement the tick timeout by one and wait for exactly one tick (600ms)
        Timing.tickTimeout--;
        return 600;
    }
}
