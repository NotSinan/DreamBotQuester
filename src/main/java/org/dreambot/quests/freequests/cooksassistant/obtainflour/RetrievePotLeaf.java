package org.dreambot.quests.freequests.cooksassistant.obtainflour;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrievePotLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return !Inventory.contains("Pot");
    }


    @Override
    public int onLoop() {
        //get bucket and pot together if not have either, because they are so close together, to save a trip
        if (!Inventory.contains("Bucket", "Bucket of milk")) {
            final Tile BUCKET_LUMBRIDGE_SPAWN = new Tile(3216, 9625, 0);
            return GroundItemHelper.pickupGroundSpawn(BUCKET_LUMBRIDGE_SPAWN, "Bucket");
        }

        final Tile POT_LUMBRIDGE_SPAWN = new Tile(3209, 3214, 0);
        return GroundItemHelper.pickupGroundSpawn(POT_LUMBRIDGE_SPAWN, "Pot");
    }

}
