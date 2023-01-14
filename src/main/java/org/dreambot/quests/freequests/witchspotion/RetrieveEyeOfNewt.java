package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.BankHelper;
import org.dreambot.utilities.helpers.ShopHelper;
import org.dreambot.utilities.helpers.WorldsHelper;

public class RetrieveEyeOfNewt extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) == 1
        && !Inventory.contains("Eye of newt");
  }

  @Override
  public int onLoop() {
    final Area PORT_SARIM_MAGE_SHOP_AREA = new Area(3011, 3261, 3016, 3256, 0);
    if (Inventory.count("Coins") < 3) {
      return BankHelper.withdrawFromBank("Coins", 5);
    }

    // highwayman aggressivity check
    if (Players.getLocal().getLevel() <= 10) {
      NPC highwayManInsideShop =
          NPCs.closest(
              n -> n.getName().equals("Highwayman") && PORT_SARIM_MAGE_SHOP_AREA.contains(n));
      if (highwayManInsideShop != null && highwayManInsideShop.exists()) {
        if (Players.getLocal().isInCombat()) {
          Timing.sleepForDelay();
          Bank.open();
          return Timing.loopReturn();
        }

        WorldsHelper.hopSimilarWorld();
        return Timing.loopReturn();
      }
    }

    return ShopHelper.purchaseFromShop(PORT_SARIM_MAGE_SHOP_AREA, "Eye of newt", 1, "Betty");
  }
}
