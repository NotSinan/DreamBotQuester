package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.OwnedItems;
import org.dreambot.utilities.helpers.BankHelper;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrieveTinderboxCorsairCurseLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return !Inventory.contains("Tinderbox") &&
                (((new Area(2543, 2864, 2547, 2860, 1).contains(Players.getLocal()) || // Gnocci upstairs area
                        (PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 30 ||
                                PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 35 ||
                                PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 40 ||
                                PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 45))) ||
                        OwnedItems.contains("Tinderbox"));
    }

    @Override
    public int onLoop() {
        if (OwnedItems.contains("Tinderbox")) {
            return BankHelper.withdrawFromBank("Tinderbox", 1);
        }
        
        return GroundItemHelper.pickupGroundSpawn(new Tile(2543, 2862, 1), "Tinderbox"); //gnocci upstairs tinderbox spawn
    }

}
