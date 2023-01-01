package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.QuestVarPlayer;

public class SolveCurseBranch extends Branch {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 15;
    }
}
