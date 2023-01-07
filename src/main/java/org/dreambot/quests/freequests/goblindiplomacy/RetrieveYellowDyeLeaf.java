package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class RetrieveYellowDyeLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.count("Onion") >= 2;
    }

    @Override
    public int onLoop() {
        Area AGGIE_AREA = new Area(3083, 3261, 3088, 3256);
        final String[] DIALOGUE_OPTIONS = {
                "Can you make dyes for me please?",
                "What do you need to make yellow dye?",
                "Okay, make me some yellow dye please."
        };
        return QuestHelper.goAndTalkToNpc(AGGIE_AREA, "Aggie", DIALOGUE_OPTIONS);
    }
}
