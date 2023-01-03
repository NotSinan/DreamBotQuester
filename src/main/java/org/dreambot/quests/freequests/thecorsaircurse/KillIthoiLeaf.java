package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.NPCHelper;

public class KillIthoiLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 52;
    }

    @Override
    public int onLoop() {
        return NPCHelper.goAndKillNpc(new Area(2527, 2841, 2532, 2835, 1), "Ithoi the Navigator");
    }

}
