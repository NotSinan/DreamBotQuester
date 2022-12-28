package org.dreambot.quests.piratestreasure.smugglerum;

import org.dreambot.api.Client;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class PauseForCutsceneLeaf extends Leaf {

    /**
     * Pause for cutscene during Pirate's Treasure (karamja boat), do nothing.
     * @return
     */

    @Override
    public boolean isValid() { return Client.isInCutscene() || Client.isDynamicRegion(); }

    @Override
    public int onLoop() { return Timing.loopReturn(); }
}
