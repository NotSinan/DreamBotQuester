package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveSpadePiratesTreasureLeaf extends Leaf {

    private final Tile FALADOR_SPADE_AREA = new Tile(2981, 3370, 0);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 0 && !Inventory.contains("Spade") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 3 && !Inventory.contains("Spade");
    }

    @Override
    public int onLoop() { return QuestHelper.pickupGroundSpawn(FALADOR_SPADE_AREA, "Spade"); }
}
