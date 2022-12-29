package org.dreambot.quests.clocktower;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveRedCogLeaf extends Leaf {

    private final Tile RED_COG_SPAWN = new Tile(2583, 9613, 0);
    private final String RED_COG = "Red cog";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_CLOCK_TOWER.getId()) == 1 && !Inventory.contains("Red cog");
    }

    @Override
    public int onLoop() {
        return QuestHelper.pickupGroundSpawn(RED_COG_SPAWN, RED_COG);
    }
}
