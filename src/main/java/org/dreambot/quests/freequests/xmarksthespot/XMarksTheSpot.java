package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.QuestManifest;
import org.dreambot.utilities.requirements.CheckRequirements;

@QuestManifest(author = "Sinan", questName = "X marks the spot")
public class XMarksTheSpot extends Branch {
  @Override
  public boolean isValid() {
    return CheckRequirements.checkRequirements(FreeQuest.X_MARKS_THE_SPOT);
  }
}
