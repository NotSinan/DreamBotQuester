package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.CurseState;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class SolveArsenFoodMystery extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 30;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(2553, 2859, 2559, 2853, 1),
                "Arsen the Thief",
                new String[]{"I hear Ithoi cooked the meal you ate that night."}
        );
    }

}
