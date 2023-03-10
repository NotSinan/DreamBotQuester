package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrieveWhiteApronLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return SmuggleState.haveShippedRum() && !Equipment.contains("White apron");
  }

  @Override
  public int onLoop() {
    if (!Equipment.contains("White apron") && Inventory.contains("White apron")) {
      Timing.sleepForDelay();
      if (Inventory.interact("White apron", "Wear")) {
        Sleep.sleepUntil(() -> Equipment.contains("White apron"), 3000);
      }
      return Timing.loopReturn();
    }

    if (Inventory.isFull()) {
      Logger.log("Inventory is full! Please empty to continue quest...");
      return -1;
    }
    final Tile SARIM_WHITE_APRON_SPAWN = new Tile(3016, 3229, 0);
    return GroundItemHelper.pickupGroundSpawn(SARIM_WHITE_APRON_SPAWN, "White apron");
  }
}
