package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GroundItemHelper;

public class RetrieveSpadeCorsairCurseLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 15
        && CurseState.talkedToIthoi()
        && CurseState.talkedToGnocci()
        && CurseState.talkedToArsen()
        && CurseState.talkedToColin()
        && !Inventory.contains("Spade");
  }

  @Override
  public int onLoop() {
    return GroundItemHelper.pickupGroundSpawn(
        new Tile(2552, 2846, 0), // corsair cove spade spawn
        "Spade");
  }
}
