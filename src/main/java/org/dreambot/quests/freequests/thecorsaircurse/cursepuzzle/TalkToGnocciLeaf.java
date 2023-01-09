package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToGnocciLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return !CurseState.talkedToGnocci() ||
                (CurseState.talkedToIthoi() &&
                        CurseState.returnedToothpick() &&
                        CurseState.lookedThroughTelescope() &&
                        CurseState.foundDoll() &&
                        TalkToTessLeaf.leaveTessArea());
    }

    @Override
    public int onLoop() {
        return NPCHelper.goAndTalkToNpc(
                new Area(2543, 2864, 2547, 2860, 1),
                "Gnocci the Cook",
                new String[]{"I hear you've been cursed."}
        );
    }

}
