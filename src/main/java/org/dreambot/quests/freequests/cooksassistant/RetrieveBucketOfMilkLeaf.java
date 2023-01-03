package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveBucketOfMilkLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 1) && !Inventory.contains("Bucket of milk");
    }

    @Override
    public int onLoop() {
        if (!Inventory.contains("Bucket")) {
            final Tile BUCKET_LUMBRIDGE_SPAWN = new Tile(3216, 9625, 0);
            return QuestHelper.pickupGroundSpawn(BUCKET_LUMBRIDGE_SPAWN, "Bucket");
        }

        final Area DAIRY_COW_WEST_LUMBRIDGE_AREA = new Area(3171, 3322, 3177, 3316, 0);
        return QuestHelper.goAndInteractWithGameObject(
                DAIRY_COW_WEST_LUMBRIDGE_AREA,
                "Dairy cow",
                "Milk",
                () -> Inventory.contains("Bucket of milk"));
    }
}
