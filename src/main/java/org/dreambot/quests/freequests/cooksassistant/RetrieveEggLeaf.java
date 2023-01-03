package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class RetrieveEggLeaf extends Leaf {
    private final Tile EGG_CHICKEN_WEST_SPAWN = new Tile(3185, 3297, 0);

    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) == 0 ||
                PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) == 1) && !Inventory.contains("Egg");
    }

    @Override
    public int onLoop() {
        return QuestHelper.pickupGroundSpawn(EGG_CHICKEN_WEST_SPAWN, "Egg");
    }

}
