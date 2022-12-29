package org.dreambot.quests.impcatcher;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedImpCatcherLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_IMP_CATCHER.getId()) >= 2;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Imp Catcher, stopping script...!");
        return -1;
    }
}
