package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.ShopHelper;

public class BucketHelper implements CommonItemsHelper {

  @Override
  public int getItem() {
    Area LUMBRIDGE_GENERAL_STORE_AREA = new Area(3206, 3252, 3215, 3241);
    return ShopHelper.purchaseFromShop(LUMBRIDGE_GENERAL_STORE_AREA, "Bucket", 1, "Shop keeper");
  }
}
