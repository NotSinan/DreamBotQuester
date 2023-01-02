package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class RetrieveRumLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return !Inventory.contains("Karamjan rum");
    }

    @Override
    public int onLoop() {
        final Area KARAMJA_PUB_AREA = new Area(
                new Tile(2930, 3142, 0),
                new Tile(2930, 3148, 0),
                new Tile(2921, 3147, 0),
                new Tile(2920, 3149, 0),
                new Tile(2917, 3149, 0),
                new Tile(2916, 3148, 0),
                new Tile(2916, 3143, 0),
                new Tile(2917, 3142, 0));
        return QuestHelper.purchaseFromShop(KARAMJA_PUB_AREA, "Karamjan rum", 1, "Zambo");
    }
}
