package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToTockCorsair1Leaf extends Leaf {

    @Override
    public boolean isValid() {
        return CurseState.talkedToIthoi() &&
                        CurseState.talkedToGnocci() &&
                        CurseState.talkedToArsen() &&
                        CurseState.talkedToColin() &&
                        Inventory.contains("Spade");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(2573, 2837, 2583, 2835, 1),
                "Captain Tock",
                new String[]{"Arsen says he gave you a sacred ogre relic.", "About that sacred ogre relic..."}
        );
    }

}
