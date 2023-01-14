package org.dreambot.quests.freequests.cooksassistant.obtainflour;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Branch;

public class RetrievePotOfFlourBranch extends Branch {
  @Override
  public boolean isValid() {
    return (PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) == 0
            || PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) == 1)
        && !Inventory.contains("Pot of flour");
  }
}
