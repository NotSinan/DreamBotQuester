package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class RetrieveBucketOfMilkLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 0 && !Inventory.contains("Bucket of milk");
    }


    @Override
    public int onLoop() {

        if (!Inventory.contains("Bucket")) {
            return QuestHelper.pickupGroundSpawn(
                    new Tile(3216, 9625, 0), //lumbridge bucket spawn
                    "Bucket"
            );
        }

        if (!QuestHelper.walkToArea(new Area(3171, 3322, 3177, 3316, 0))) { //west lumbridge dairy cow area
            return Timing.loopReturn();
        }

        GameObject dairyCow = GameObjects.closest(g -> g.getName().equals("Dairy cow") && g.hasAction("Milk"));
        if (dairyCow != null && Interaction.delayEntityInteract(dairyCow, "Milk")) {
            Sleep.sleepUntil(() -> Inventory.contains("Bucket of milk"), () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
