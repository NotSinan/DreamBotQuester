package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class KillIthoiLeaf extends Leaf {
    private final Area ITHOI_AREA = new Area(2527, 2841, 2532, 2835, 1);
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 52;
    }

    @Override
    public int onLoop() { return QuestHelper.goAndKillNpc(ITHOI_AREA, "Ithoi the Navigator"); }

}