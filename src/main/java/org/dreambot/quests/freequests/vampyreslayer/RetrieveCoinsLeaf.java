package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.OwnedItems;
import org.dreambot.utilities.helpers.BankHelper;

public class RetrieveCoinsLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return Inventory.count("Coins") < 50 && !OwnedItems.contains("Hammer");
  }

  @Override
  public int onLoop() {
    return BankHelper.withdrawFromBank("Coins", 100);
  }
}
