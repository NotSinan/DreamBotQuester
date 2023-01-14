package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RedBerryHelper implements CommonItemsHelper {
  @Override
  public int getItem() {
    final Area RED_BERRY_BUSH_AREA = new Area(3266, 3375, 3279, 3365);
    final int count = Inventory.count("Redberries");
    return GameObjectHelper.goAndInteractWithGameObject(
        RED_BERRY_BUSH_AREA,
        "Redberry bush",
        "Pick-from",
        () -> Inventory.count("Redberries") != count);
  }
}
