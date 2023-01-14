package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GroundItemHelper;

/**
 * This class picks up the bucket that spawns upstairs in Varrock palace.
 */
public class RetrieveBucketLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2
        && !Inventory.contains("Bucket")
        && !Inventory.contains("Bucket of water")
        && !Inventory.contains(2401)
        && // Silverlight key ID.
        PlayerSettings.getBitValue(2568)
            == 0; // Player setting to determine if a player has poured water in the drain.
  }

  @Override
  public int onLoop() {
    final Tile BUCKET_SPAWN_TILE =
        new Tile(3221, 3497, 1); // Bucket spawn upstairs in Varrock palace.
    return GroundItemHelper.pickupGroundSpawn(BUCKET_SPAWN_TILE, "Bucket");
  }
}
