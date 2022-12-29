package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.Timing;

@QuestManifest(author = "420", questName = "Cook's assistant")
public class CooksAssistant extends Branch {

    @Override
    public boolean isValid() { return Timing.isValidTick(); }
}
