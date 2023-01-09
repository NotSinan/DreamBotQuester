package org.dreambot.quests.freequests.cooksassistant.obtainflour;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RetrieveGrainLeaf extends Leaf {

    @Override
    public boolean isValid() {
        final int GRAIN_TOWER_FLOUR_COUNT_VARBIT = 4920; //Indicates the quantity of flour available to take from bin in west lumbridge grain tower
        return !Inventory.contains("Grain") && PlayerSettings.getBitValue(GRAIN_TOWER_FLOUR_COUNT_VARBIT) <= 0;
    }


    @Override
    public int onLoop() {
        final Area WHEAT_FIELD_AREA = new Area(
                new Tile(3161, 3293, 0),
                new Tile(3155, 3297, 0),
                new Tile(3154, 3305, 0),
                new Tile(3158, 3304, 0),
                new Tile(3162, 3299, 0));
        return GameObjectHelper.goAndInteractWithGameObject(
                WHEAT_FIELD_AREA,
                "Wheat",
                "Pick",
                () -> Inventory.contains("Grain")
        );

    }

}
