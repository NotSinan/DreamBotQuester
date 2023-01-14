package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GameObjectHelper;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrieveBucketOfMilkLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return (PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) == 0
            || PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) == 1)
        && !Inventory.contains("Bucket of milk");
  }

  @Override
  public int onLoop() {
    if (!Inventory.contains("Bucket")) {
      final Tile BUCKET_LUMBRIDGE_SPAWN = new Tile(3216, 9625, 0);
      return GroundItemHelper.pickupGroundSpawn(BUCKET_LUMBRIDGE_SPAWN, "Bucket");
    }

    final Area DAIRY_COW_WEST_LUMBRIDGE_AREA = new Area(3171, 3322, 3177, 3316, 0);
    return GameObjectHelper.goAndInteractWithGameObject(
        DAIRY_COW_WEST_LUMBRIDGE_AREA,
        "Dairy cow",
        "Milk",
        () -> Inventory.contains("Bucket of milk"));
  }
}
