package org.dreambot.behaviour.fallback;

import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class FallbackLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        return Timing.loopReturn();
    }
}
