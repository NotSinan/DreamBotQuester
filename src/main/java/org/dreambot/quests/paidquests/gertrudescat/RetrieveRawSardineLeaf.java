package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.ShopHelper;

public class RetrieveRawSardineLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 0 && !Inventory.contains("Raw sardine");
    }

    @Override
    public int onLoop() {
        return ShopHelper.purchaseFromShop(
                new Area(3011, 3229, 3017, 3223),
                "Raw sardine",
                1,
                "Gerrant");
    }
}
