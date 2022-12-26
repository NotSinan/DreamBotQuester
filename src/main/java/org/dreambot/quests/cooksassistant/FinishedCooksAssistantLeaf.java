package org.dreambot.quests.cooksassistant;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedCooksAssistantLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) >= 2;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Cook's Assistant! Stopping script...!");
        return -1;
    }
}
