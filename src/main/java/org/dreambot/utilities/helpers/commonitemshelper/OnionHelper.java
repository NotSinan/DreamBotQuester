package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class OnionHelper implements CommonItemsHelper{
    @Override
    public int getItem() {
        final Area ONION_AREA = new Area(3186, 3269, 3191, 3265);
        final int count = Inventory.count("Onion") + 1;
        return GameObjectHelper.goAndInteractWithGameObject(ONION_AREA, "Onion", "Pick", () -> Inventory.count("Onion") != count);
    }
}
