package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToTockCorsair3Leaf extends Leaf {
    @Override
    public boolean isValid() { return PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 50; }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(2573, 2837, 2583, 2835, 1),
                "Captain Tock",
                new String[]{"I've seen Ithoi running around. He's not sick at all."}
        );
    }


}
