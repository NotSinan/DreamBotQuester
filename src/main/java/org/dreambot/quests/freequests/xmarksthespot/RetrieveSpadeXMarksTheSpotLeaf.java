package org.dreambot.quests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveSpadeXMarksTheSpotLeaf extends Leaf {

    private final Tile FALADOR_SPADE_AREA = new Tile(2981, 3370, 0);

    @Override
    public boolean isValid() {
        return !Inventory.contains("Spade") && PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 0;
    }

    @Override
    public int onLoop() { return QuestHelper.pickupGroundSpawn(FALADOR_SPADE_AREA, "Spade"); }
}
