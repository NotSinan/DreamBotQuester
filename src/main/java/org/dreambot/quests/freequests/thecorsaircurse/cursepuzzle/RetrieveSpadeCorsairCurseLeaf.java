package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveSpadeCorsairCurseLeaf extends Leaf {

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
        return QuestHelper.pickupGroundSpawn(
                new Tile(2552, 2846, 0), //corsair cove spade spawn
                "Spade"
        );
    }

}
