package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class FillBucketLeaf extends Leaf {

    private final Area SINK_AREA = new Area(3218, 3497, 3224, 3491);

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 2 &&
                Inventory.contains("Bucket") &&
                PlayerSettings.getBitValue(2568) != 1 &&
                !Inventory.contains(2401);
    }

    @Override
    public int onLoop() {

        if (!SINK_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(SINK_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GameObject sink = GameObjects.closest("Sink");
        if (sink != null && Inventory.interact("Bucket", "Use")) {
            sink.interact("Use");
            Sleep.sleepUntil(() -> Inventory.contains("Bucket of water"), 3000);
        }
        return Timing.loopReturn();
    }
}
