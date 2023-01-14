package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;

import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.quest.book.MiniQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class WithdrawFromBankAlfredGrimhandsBarcrawlLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return Bank.isOpen();
  }

  @Override
  public int onLoop() {
    switch (PlayerSettings.getConfig(MiniQuest.ALFRED_GRIMHANDS_BARCRAWL.getConfigID())) {
      case (0):
        {
          // placeholder for teleportation withdrawal handling
          break;
        }
    }
    if (Bank.close()) {
      Sleep.sleepUntil(() -> !Bank.isOpen(), 3000);
    }
    return Timing.loopReturn();
  }
}
