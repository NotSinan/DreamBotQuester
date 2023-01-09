package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrieveSpadePiratesTreasureLeaf extends Leaf {

    private final Tile FALADOR_SPADE_AREA = new Tile(2981, 3370, 0);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 0 && !Inventory.contains("Spade") ||
                PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 3 && !Inventory.contains("Spade");
    }

    @Override
    public int onLoop() { return GroundItemHelper.pickupGroundSpawn(FALADOR_SPADE_AREA, "Spade"); }
}
