package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.framework.Branch;
import org.dreambot.utilities.Timing;

public class CooksAssistant extends Branch {

    @Override
    public boolean isValid() { return Timing.isValidTick(); }
}
