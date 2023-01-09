package org.dreambot.quests.freequests.cooksassistant.obtainflour;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.GameObjectHelper;
import org.dreambot.utilities.helpers.WalkingHelper;

public class OperateGrainMillLeaf extends Leaf {

    private static boolean HOPPER_LOADED = false; //flips to true upon putting grain in hopper

    @Override
    public boolean isValid() {
        return true;
    }


    @Override
    public int onLoop() {
        final int GRAIN_TOWER_FLOUR_COUNT_VARBIT = 4920; //Indicates the quantity of flour available to take from bin in west lumbridge grain tower
        final Area GRAIN_TOWER_LVL1_AREA = new Area(
                new Tile(3168, 3303, 0),
                new Tile(3166, 3303, 0),
                new Tile(3163, 3306, 0),
                new Tile(3166, 3310, 0),
                new Tile(3167, 3310, 0),
                new Tile(3170, 3307, 0),
                new Tile(3170, 3306, 0));
        if (PlayerSettings.getBitValue(GRAIN_TOWER_FLOUR_COUNT_VARBIT) > 0) {


            return GameObjectHelper.goAndInteractWithGameObject(
                    GRAIN_TOWER_LVL1_AREA,
                    "Flour bin",
                    "Empty",
                    () -> Inventory.contains("Pot of flour"));
        }

        final Area GRAIN_TOWER_LVL3_AREA = new Area(3164, 3309, 3169, 3304, 2);
        if (!WalkingHelper.walkToArea(GRAIN_TOWER_LVL3_AREA)) {
            return Timing.getSleepDelay();
        }

        if (!HOPPER_LOADED) {
            int count = Inventory.count("Grain");
            return GameObjectHelper.goAndInteractWithGameObject(
                    GRAIN_TOWER_LVL3_AREA,
                    "Hopper",
                    "Fill",
                    () -> Inventory.count("Grain") < count);
        }
        return GameObjectHelper.goAndInteractWithGameObject(
                GRAIN_TOWER_LVL3_AREA,
                "Hopper controls",
                "Operate",
                () -> PlayerSettings.getBitValue(GRAIN_TOWER_FLOUR_COUNT_VARBIT) > 0);
    }

}
