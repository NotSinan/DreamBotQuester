package org.dreambot.quests.xmarksthespot;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarBits;

public class FinishedXMarksTheSpotLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) > 7;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished X Marks The Spot, stopping script...!");
        return -1;
    }
}
