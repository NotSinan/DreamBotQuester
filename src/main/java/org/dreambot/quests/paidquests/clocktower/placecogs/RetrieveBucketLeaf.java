package org.dreambot.quests.paidquests.clocktower.placecogs;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.WalkingHelper;

public class RetrieveBucketLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return ((!CogState.placedBlueCog && !Inventory.contains("Blue cog")) ||
                (!CogState.placedBlackCog && !Inventory.contains("Black cog"))) &&
                !Inventory.contains("Bucket of water");
    }

    @Override
    public int onLoop() {
        if (!Inventory.contains("Bucket")) {
            return QuestHelper.pickupGroundSpawn(new Tile(2616, 3255, 0), "Bucket");
        }

        final Area WELL_AREA = new Area(
                new Tile(2608, 3257, 0),
                new Tile(2611, 3256, 0),
                new Tile(2611, 3261, 0),
                new Tile(2617, 3261, 0),
                new Tile(2617, 3251, 0),
                new Tile(2608, 3251, 0));
        if (!WalkingHelper.walkToArea(WELL_AREA)) {
            return Timing.getSleepDelay();
        }

        GameObject well = GameObjects.closest("Well");
        Item bucket = Inventory.get("Bucket");
        if (well != null && well.exists() && bucket != null && bucket.isValid() && Interaction.delayUseItemOn(bucket, well)) {
            Sleep.sleepUntil(() -> Inventory.contains("Bucket of water"), () -> Players.getLocal().isMoving(), 3000, 100);
        }

        return Timing.loopReturn();

    }

}
