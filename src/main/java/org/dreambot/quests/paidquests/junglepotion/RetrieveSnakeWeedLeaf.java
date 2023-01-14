package org.dreambot.quests.paidquests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RetrieveSnakeWeedLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 1
        && !Inventory.contains("Snake weed");
  }

  @Override
  public int onLoop() {
    final String ITEM = "Grimy snake weed";

    if (Inventory.contains(ITEM)) {
      if (Inventory.interact(ITEM, "Clean")) {
        Sleep.sleepUntil(() -> Inventory.contains("Snake weed"), 3000);
      }
      return Timing.loopReturn();
    }

    return GameObjectHelper.goAndInteractWithGameObject(
        new Area(2759, 3043, 2773, 3034),
        "Marshy jungle vine",
        "Search",
        () -> Inventory.contains(ITEM));
  }
}
