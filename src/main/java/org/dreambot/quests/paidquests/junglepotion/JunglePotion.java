package org.dreambot.quests.paidquests.junglepotion;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class JunglePotion extends Branch {
  @Override
  public boolean isValid() {
    return CheckRequirements.checkRequirements(PaidQuest.JUNGLE_POTION);
  }
}
