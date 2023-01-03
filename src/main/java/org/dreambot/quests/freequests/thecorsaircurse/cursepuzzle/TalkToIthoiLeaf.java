package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.NPCHelper;

public class TalkToIthoiLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return !CurseState.talkedToIthoi();
    }

    @Override
    public int onLoop() {
        final Area ITHOI_AREA = new Area(2527, 2841, 2532, 2835, 1);
        return NPCHelper.goAndTalkToNpc(
                ITHOI_AREA,
                "Ithoi the Navigator",
                new String[]{"I hear you've been cursed."}
        );
    }

}
