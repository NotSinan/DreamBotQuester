package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.CurseState;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class SolveGnocciFoodMysteryLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 25;
    }


    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(2543, 2864, 2547, 2860, 1),
                "Arsen the Thief",
                new String[]{"I hear it happened straight after dinner."}
        );
    }
}
