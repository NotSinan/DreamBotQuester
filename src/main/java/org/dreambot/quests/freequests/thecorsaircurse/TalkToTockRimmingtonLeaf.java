package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToTockRimmingtonLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 10;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
               new Area(2906, 3227, 2915, 3225, 0),
                "Captain Tock",
               new String[]{"Okay, I'm ready go to Corsair Cove."}
        );
    }

}
