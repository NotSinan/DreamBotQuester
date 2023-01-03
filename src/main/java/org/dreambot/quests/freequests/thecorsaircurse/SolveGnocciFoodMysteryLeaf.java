package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.NPCHelper;

public class SolveGnocciFoodMysteryLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 25;
    }


    @Override
    public int onLoop() {
        return NPCHelper.goAndTalkToNpc(
                new Area(2543, 2864, 2547, 2860, 1),
                "Arsen the Thief",
                new String[]{"I hear it happened straight after dinner."}
        );
    }
}
