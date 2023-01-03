package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.WalkingHelper;

public class FillBucketLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2 &&
                Inventory.contains("Bucket") &&
                PlayerSettings.getBitValue(2568) != 1 &&
                !Inventory.contains(2401);
    }

    @Override
    public int onLoop() {
        final Area SINK_AREA = new Area(3218, 3497, 3224, 3491);
        if (!WalkingHelper.walkToArea(SINK_AREA)) {
            return Timing.getSleepDelay();
        }

        GameObject sink = GameObjects.closest("Sink");
        Item bucket = Inventory.get("Bucket");
        if (sink != null && sink.exists() && bucket != null && bucket.isValid() &&
                Interaction.delayUseItemOn(bucket, sink)) {
            Sleep.sleepUntil(() -> Inventory.contains("Bucket of water"), () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
