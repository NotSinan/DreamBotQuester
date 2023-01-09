package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrieveSpadeXMarksTheSpotLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return !Inventory.contains("Spade") && PlayerSettings.getBitValue(FreeQuest.X_MARKS_THE_SPOT.getVarBitID()) == 0;
    }

    @Override
    public int onLoop() {
        final Tile FALADOR_SPADE_AREA = new Tile(2981, 3370, 0);
        return GroundItemHelper.pickupGroundSpawn(FALADOR_SPADE_AREA, "Spade");
    }
}
