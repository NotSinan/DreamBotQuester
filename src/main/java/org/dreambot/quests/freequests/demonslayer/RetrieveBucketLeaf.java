package org.dreambot.quests.freequests.demonslayer;


import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveBucketLeaf extends Leaf {

    private final Tile BUCKET_TILE = new Tile(3221, 3497, 1);
    private final String BUCKET = "Bucket";

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 2 &&
                !Inventory.contains("Bucket") &&
                !Inventory.contains("Bucket of water") &&
                !Inventory.contains(2401) &&
                PlayerSettings.getBitValue(2568) == 0;
    }

    @Override
    public int onLoop() {
        return QuestHelper.pickupGroundSpawn(BUCKET_TILE, BUCKET);
    }
}
