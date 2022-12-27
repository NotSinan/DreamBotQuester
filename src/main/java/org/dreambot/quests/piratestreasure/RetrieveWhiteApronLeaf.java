package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveWhiteApronLeaf extends Leaf {
    private final Area PORT_SARIM_FOOD_SHOP_AREA = new Area(
            new Tile(3016, 3203, 0),
            new Tile(3012, 3203, 0),
            new Tile(3012, 3210, 0),
            new Tile(3016, 3207, 0));

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 0 &&
                !Equipment.contains("White apron");
    }

    @Override
    public int onLoop() {
        if (Inventory.contains("White apron")) {
            if(Shop.isOpen()) {
                if(Shop.close()) return Timing.loopReturn();
                return Timing.getSleepDelay();
            }

            if (Inventory.interact("White apron", "Wear")) {
                Sleep.sleepUntil(() -> Equipment.contains("White apron"), 3000);
            }
            return Timing.loopReturn();
        }

        return QuestHelper.purchaseFromShop(PORT_SARIM_FOOD_SHOP_AREA, "White apron", 1, "Wydin");
    }
}
