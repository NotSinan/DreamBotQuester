package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToTockCorsair3Leaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 50;
    }

    @Override
    public int onLoop() {
        return NPCHelper.goAndTalkToNpc(
                new Area(2573, 2837, 2583, 2835, 1),
                "Captain Tock",
                new String[]{"I've seen Ithoi running around. He's not sick at all."}
        );
    }


}
