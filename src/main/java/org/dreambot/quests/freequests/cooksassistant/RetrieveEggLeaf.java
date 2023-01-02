package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveEggLeaf extends Leaf {
    private final Tile EGG_CHICKEN_WEST_SPAWN = new Tile(3185, 3297, 0);

    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 1) && !Inventory.contains("Egg");
    }

    @Override
    public int onLoop() {
        return QuestHelper.pickupGroundSpawn(EGG_CHICKEN_WEST_SPAWN, "Egg");
    }

}
