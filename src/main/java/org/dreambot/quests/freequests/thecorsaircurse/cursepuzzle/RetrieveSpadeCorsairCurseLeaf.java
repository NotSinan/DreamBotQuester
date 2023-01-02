package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveSpadeCorsairCurseLeaf extends Leaf {
    private final Tile SPADE_TILE = new Tile(2552, 2846, 0);

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 15 &&
                CurseState.talkedToIthoi() &&
                CurseState.talkedToGnocci() &&
                CurseState.talkedToArsen() &&
                CurseState.talkedToColin() &&
                !Inventory.contains("Spade");
    }

    @Override
    public int onLoop() {
        return QuestHelper.pickupGroundSpawn(SPADE_TILE, "Spade");
    }

}
