package org.dreambot.quests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveBucketOfMilkLeaf extends Leaf {

    private final Tile BUCKET_LUMBRIDGE_SPAWN = new Tile(3216, 9625, 0);
    private final Area DAIRY_COW_WEST_LUMBRIDGE_AREA = new Area(3171, 3322, 3177, 3316, 0);

    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 1) && !Inventory.contains("Bucket of milk");
    }


    @Override
    public int onLoop() {
        if(!Inventory.contains("Bucket")) {
            return QuestHelper.pickupGroundSpawn(BUCKET_LUMBRIDGE_SPAWN,"Bucket");
        }

        if(!DAIRY_COW_WEST_LUMBRIDGE_AREA.contains(Players.getLocal())) {
            if(Walking.shouldWalk(6)) {
                Interaction.delayWalk(DAIRY_COW_WEST_LUMBRIDGE_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GameObject dairyCow = GameObjects.closest(g -> g.getName().equals("Dairy cow") && g.hasAction("Milk"));
        if(dairyCow != null && Interaction.delayEntityInteract(dairyCow, "Milk")) {
            Sleep.sleepUntil(() -> Inventory.contains("Bucket of milk"), () -> Players.getLocal().isMoving(), 3000, 100);
        }

        return Timing.loopReturn();
    }
}
