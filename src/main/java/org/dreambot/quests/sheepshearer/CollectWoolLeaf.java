package org.dreambot.quests.sheepshearer;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class CollectWoolLeaf extends Leaf {
    private final Area SHEEP_AREA = new Area(3193, 3276, 3211, 3257);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(179) == 1 &&
                Inventory.count("Wool") != 20 &&
                Inventory.count("Ball of wool") <= 20;
    }

    @Override
    public int onLoop() {
        if (!Inventory.contains("Shears")) {
            GroundItem shears = GroundItems.closest(item -> item.getName().equals("Shears"));
            if (shears != null && shears.interact("Take")) {
                Sleep.sleepUntil(() -> Inventory.contains("Shears"), 3000);
            }
            return Timing.loopReturn();
        }

        if (!SHEEP_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(SHEEP_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        NPC sheep = NPCs.closest(npc -> npc.getName().equals("Sheep"));
        if (sheep != null && sheep.interact("Shear")) {
            Sleep.sleepUntil(() -> Inventory.contains("Wool"), 3000);
        }
        return Timing.loopReturn();
    }
}