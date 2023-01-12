package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToVeosSarimLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.X_MARKS_THE_SPOT.getVarBitID()) == 6 && Inventory.contains("Ancient casket") ||
                PlayerSettings.getBitValue(FreeQuest.X_MARKS_THE_SPOT.getVarBitID()) == 7;
    }

    @Override
    public int onLoop() {
        final Area VEOS_PORT_SARIM_AREA = new Area(3050, 3249, 3055, 3245);
        final String VEOS_NAME = "Veos";
        return NPCHelper.goAndTalkToNpc(VEOS_PORT_SARIM_AREA, VEOS_NAME, null);
    }
}
