package org.dreambot.quests.sheepshearer;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedSheepShearerLeaf extends Leaf {

    @Override
    public boolean isValid() { return PlayerSettings.getConfig(QuestVarPlayer.QUEST_SHEEP_SHEARER.getId()) > 20; }

    @Override
    public int onLoop() {
        Logger.log("Finished Sheep Shearer, stopping script...!");
        return -1;
    }
}
