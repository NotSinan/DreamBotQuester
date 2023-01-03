package org.dreambot.quests.freequests.cooksassistant.obtainflour;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrievePotOfFlourBranch extends Branch {
    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 1) &&
                !Inventory.contains("Pot of flour");
    }
}
