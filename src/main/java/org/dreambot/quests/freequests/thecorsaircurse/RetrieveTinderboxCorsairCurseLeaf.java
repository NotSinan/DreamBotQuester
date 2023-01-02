package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveTinderboxCorsairCurseLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return !Inventory.contains("Tinderbox") &&
                (new Area(2543, 2864, 2547, 2860, 1).contains(Players.getLocal()) || // Gnocci upstairs area
                        (PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 30 ||
                                PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 35 ||
                                PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 40 ||
                                PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 45));
    }

    @Override
    public int onLoop() {
        return QuestHelper.pickupGroundSpawn(new Tile(2543, 2862, 1), "Tinderbox"); //gnocci upstairs tinderbox spawn
    }

}
