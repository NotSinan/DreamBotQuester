package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrieveDoogleLeavesLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 0
        && !Inventory.contains("Doogle leaves");
  }

  @Override
  public int onLoop() {
    return GroundItemHelper.pickupGroundSpawn(
        new Tile(3152, 3399, 0), // doogle leaves spawn
        "Doogle leaves");
  }
}
