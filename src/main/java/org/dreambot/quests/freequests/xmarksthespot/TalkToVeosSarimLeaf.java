package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToVeosSarimLeaf extends Leaf {

    private final Area VEOS_PORT_SARIM_AREA = new Area(3050, 3249, 3055, 3245);
    private final String VEOS_NAME = "Veos";
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 6 && Inventory.contains("Ancient casket") ||
                PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 7;
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(VEOS_PORT_SARIM_AREA, VEOS_NAME, null); }
}
