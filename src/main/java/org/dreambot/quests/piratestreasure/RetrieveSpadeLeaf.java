package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveSpadeLeaf extends Leaf {

    private final Tile FALADOR_SPADE_AREA = new Tile(2981, 3370, 0);

    @Override
    public boolean isValid() {
        return !Inventory.contains("Spade") && PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 0;
    }

    @Override
    public int onLoop() { return QuestHelper.pickupGroundSpawn(FALADOR_SPADE_AREA, "Spade"); }
}
