package org.dreambot.utilities.helpers;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class ShopHelper {
  public static int purchaseFromShop(Area area, String itemName, int quantity, String npcName) {
    if (!WalkingHelper.walkToArea(area)) {
      return Timing.getSleepDelay();
    }
    if (Shop.isOpen()) {
      Item item = Shop.get(itemName);
      if (item != null && item.isValid() && item.getAmount() > 0) {
        Timing.sleepForDelay();
        if (Shop.purchase(itemName, quantity)) {
          Sleep.sleepUntil(() -> Inventory.contains(itemName), 3000);
        }
      }
      return Timing.loopReturn();
    }

    NPC shopAssistant = NPCs.closest(npcName);
    if (shopAssistant != null && Interaction.delayEntityInteract(shopAssistant, "Trade")) {
      Sleep.sleepUntil(() -> Shop.isOpen(), () -> Players.getLocal().isMoving(), 3000, 100);
    }
    return Timing.loopReturn();
  }
}
