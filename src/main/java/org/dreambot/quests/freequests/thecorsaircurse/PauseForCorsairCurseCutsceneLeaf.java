package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.Client;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class PauseForCorsairCurseCutsceneLeaf extends Leaf {

    /**
     * Pause for cutscene during Corsair Curse, do nothing.
     * @return
     */

    @Override
    public boolean isValid() { return Client.isInCutscene(); }

    @Override
    public int onLoop() { return Timing.loopReturn(); }
}
