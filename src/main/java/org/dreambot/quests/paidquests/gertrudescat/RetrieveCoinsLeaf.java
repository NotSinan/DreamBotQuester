package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.BankHelper;

public class RetrieveCoinsLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 0
        && Inventory.count("Coins") < 500;
  }

  @Override
  public int onLoop() {
    return BankHelper.withdrawFromBank("Coins", 10000);
  }
}
