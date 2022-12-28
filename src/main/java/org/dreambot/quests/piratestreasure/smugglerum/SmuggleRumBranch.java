package org.dreambot.quests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Branch;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class SmuggleRumBranch extends Branch {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 1;
    }
}
