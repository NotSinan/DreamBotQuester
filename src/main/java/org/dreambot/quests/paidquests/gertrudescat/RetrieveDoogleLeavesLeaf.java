package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveDoogleLeavesLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 0 && !Inventory.contains("Doogle leaves");
    }

    @Override
    public int onLoop() {
        return QuestHelper.pickupGroundSpawn(
                new Tile(3152, 3399, 0), //doogle leaves spawn
                "Doogle leaves"
        );
    }
}
