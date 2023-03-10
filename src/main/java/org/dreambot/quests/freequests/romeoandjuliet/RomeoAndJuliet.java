package org.dreambot.quests.freequests.romeoandjuliet;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "Sinan", questName = "Romeo and juliet")
public class RomeoAndJuliet extends Branch {
  @Override
  public boolean isValid() {
    return CheckRequirements.checkRequirements(FreeQuest.ROMEO_AND_JULIET);
  }
}
