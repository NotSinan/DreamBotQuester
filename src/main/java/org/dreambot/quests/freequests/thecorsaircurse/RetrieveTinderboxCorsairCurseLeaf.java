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
    private final Area GNOCCI_AREA = new Area(2543, 2864, 2547, 2860, 1);
    private final Tile TINDERBOX_GNOCCI_SPAWN = new Tile(2543, 2862, 1);

    @Override
    public boolean isValid() {
        return !Inventory.contains("Tinderbox") &&
                (GNOCCI_AREA.contains(Players.getLocal()) ||
                        (PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 30 ||
                                PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 35 ||
                                PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 40 ||
                                PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 45));
    }

    @Override
    public int onLoop() {
        return QuestHelper.pickupGroundSpawn(TINDERBOX_GNOCCI_SPAWN, "Tinderbox");
    }

}
