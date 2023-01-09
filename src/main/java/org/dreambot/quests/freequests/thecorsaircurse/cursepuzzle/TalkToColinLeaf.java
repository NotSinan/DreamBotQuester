package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToColinLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return !CurseState.talkedToColin() ||
                (CurseState.talkedToIthoi() &&
                        CurseState.finishedArsen() &&
                        CurseState.finishedGnocci() &&
                        CurseState.lookedThroughTelescope() &&
                        TalkToTessLeaf.leaveTessArea());
    }

    @Override
    public int onLoop() {
        return NPCHelper.goAndTalkToNpc(
                new Area(2553, 2859, 2559, 2853, 1),
                "Cabin Boy Colin",
                new String[]{"I hear you've been cursed."}
        );
    }

}
