package org.dreambot.quests.freequests.doricsquest;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrievePickaxeLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return !Inventory.contains("Bronze pickaxe", "Iron pickaxe") && !Equipment.contains("Bronze pickaxe", "Iron pickaxe");
    }

    @Override
    public int onLoop() {
        final Tile BRONZE_PICKAXE_FALADOR_SPAWN = new Tile(3009, 3342, 0);
        return GroundItemHelper.pickupGroundSpawn(BRONZE_PICKAXE_FALADOR_SPAWN, "Bronze pickaxe");
    }
}
