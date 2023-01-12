package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.ShopHelper;

public class RetrieveIronChainbodyLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.BLACK_KNIGHTS_FORTRESS.getConfigID()) == 0 &&
                Inventory.count("Coins") >= 300 &&
                !Inventory.contains("Iron chainbody");
    }

    @Override
    public int onLoop() {
        final Area HORVIK_AREA = new Area(3227, 3441, 3232, 3433);
        return ShopHelper.purchaseFromShop(
                HORVIK_AREA,
                "Iron chainbody",
                1,
                "Horvik");
    }
}
