package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class FinishedDruidicRitualLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) > 3;
    }

    @Override
    public int onLoop() {
        Logger.log("Finished Druidic Ritual, stopping script...!");
        return -1;
    }
}
