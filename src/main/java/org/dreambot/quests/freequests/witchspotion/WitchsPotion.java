package org.dreambot.quests.witchspotion;

import org.dreambot.framework.Branch;
import org.dreambot.utilities.Timing;

public class WitchsPotion extends Branch {

    @Override
    public boolean isValid() { return Timing.isValidTick(); }
}
