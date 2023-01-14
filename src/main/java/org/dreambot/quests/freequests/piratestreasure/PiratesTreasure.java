package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "420", questName = "Pirate's treasure")
public class PiratesTreasure extends Branch {
  @Override
  public boolean isValid() {
    return CheckRequirements.checkRequirements(FreeQuest.PIRATES_TREASURE);
  }
}
