package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.ShopHelper;

public class PinkSkirtHelper implements CommonItemsHelper {
    @Override
    public int getItem() {
        Area THESSALIA_AREA = new Area(3201, 3420, 3209, 3410);
        return ShopHelper.purchaseFromShop(THESSALIA_AREA, "Pink skirt", 1, "Thessalia");
    }
}
