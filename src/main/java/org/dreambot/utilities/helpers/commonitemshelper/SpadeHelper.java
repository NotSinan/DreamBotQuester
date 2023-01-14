package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.map.Tile;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class SpadeHelper implements CommonItemsHelper {
  @Override
  public int getItem() {
    final Tile FALADOR_SPADE_TILE = new Tile(2981, 3370, 0);
    return GroundItemHelper.pickupGroundSpawn(FALADOR_SPADE_TILE, "Spade");
  }
}
