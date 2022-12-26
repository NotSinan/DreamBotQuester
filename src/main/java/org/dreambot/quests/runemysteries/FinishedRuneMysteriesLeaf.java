package org.dreambot.quests.runemysteries;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedRuneMysteriesLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) > 5;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Rune Mysteries, stopping script...!");
        return -1;
    }
}
