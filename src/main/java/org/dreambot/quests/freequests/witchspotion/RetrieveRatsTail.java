package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.helpers.NPCHelper;
import org.dreambot.utilities.helpers.WalkingHelper;

public class RetrieveRatsTail extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) == 1
        && !Inventory.contains("Rat's tail");
  }

  @Override
  public int onLoop() {
    final Area PORT_SARIM_RAT_AREA =
        new Area(
            new Tile(3012, 3196, 0),
            new Tile(3001, 3196, 0),
            new Tile(3001, 3173, 0),
            new Tile(3021, 3172, 0),
            new Tile(3021, 3178, 0),
            new Tile(3010, 3179, 0),
            new Tile(3010, 3182, 0),
            new Tile(3020, 3182, 0),
            new Tile(3020, 3187, 0),
            new Tile(3013, 3188, 0));
    if (!WalkingHelper.walkToArea(PORT_SARIM_RAT_AREA)) {
      return Timing.getSleepDelay();
    }

    GroundItem ratTail =
        GroundItems.closest(
            g -> g.getName().equals("Rat's tail") && PORT_SARIM_RAT_AREA.contains(g));
    if (ratTail != null && ratTail.exists()) {
      if (Interaction.delayEntityInteract(ratTail, "Take")) {
        Sleep.sleepUntil(
            () -> Inventory.contains("Rat's tail"), () -> Players.getLocal().isMoving(), 3000, 100);
      }
      return Timing.loopReturn();
    }

    return NPCHelper.goAndKillNpc(PORT_SARIM_RAT_AREA, "Rat");
  }
}
