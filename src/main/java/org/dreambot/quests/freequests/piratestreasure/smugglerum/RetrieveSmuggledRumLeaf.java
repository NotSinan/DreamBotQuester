package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class RetrieveSmuggledRumLeaf extends Leaf {
  @Override
  public boolean isValid() {
    final Area FOOD_SHOP_INNER_AREA = new Area(3009, 3210, 3011, 3203, 0);
    return FOOD_SHOP_INNER_AREA.contains(Players.getLocal()) && !Inventory.contains("Karamjan rum");
  }

  @Override
  public int onLoop() {
    final Tile RUM_CRATE_TILE = new Tile(3009, 3207, 0);
    GameObject rumCrate =
        GameObjects.closest(g -> g.getTile().equals(RUM_CRATE_TILE) && g.getName().equals("Crate"));
    if (rumCrate != null && rumCrate.interact("Search")) {
      Sleep.sleepUntil(
          () -> Dialogues.inDialogue(), () -> Players.getLocal().isMoving(), 8000, 100);
    }
    return Timing.loopReturn();
  }
}
