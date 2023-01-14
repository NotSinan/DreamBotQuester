package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "420", questName = "Witch's potion")
public class WitchsPotion extends Branch {

  @Override
  public boolean isValid() {
    return CheckRequirements.checkRequirements(FreeQuest.WITCHS_POTION);
  }
}
