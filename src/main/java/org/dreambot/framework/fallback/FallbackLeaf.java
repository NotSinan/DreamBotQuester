package org.dreambot.framework.fallback;

import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class FallbackLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        //By calling Timing.loopReturn() we set a net tickTimeout and wait one tick.
        return Timing.loopReturn();
    }
}
