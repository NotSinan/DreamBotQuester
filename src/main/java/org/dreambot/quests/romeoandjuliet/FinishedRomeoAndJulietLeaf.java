package org.dreambot.quests.romeoandjuliet;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedRomeoAndJulietLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) >= 100;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Romeo And Juliet, stopping script...!");
        return -1;
    }
}
