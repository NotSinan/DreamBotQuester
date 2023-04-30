package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class BucketOfWaterHelper implements CommonItemsHelper{
    @Override
    public int getItem() {
        Area LUMBRIDGE_FOUNTAIN_AREA = new Area(3219, 3229, 3224, 3224);
        GameObjectHelper.goAndUseItemOnGameObject(LUMBRIDGE_FOUNTAIN_AREA, "Bucket", "Fountain");
        return 0;
    }
}
