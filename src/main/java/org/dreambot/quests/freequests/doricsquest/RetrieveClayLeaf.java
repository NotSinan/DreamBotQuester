package org.dreambot.quests.freequests.doricsquest;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

import java.util.Arrays;

public class RetrieveClayLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return Inventory.count("Clay") < 6;
    }

    @Override
    public int onLoop() {
        final Area RIMMINGTON_CLAY_ROCKS_AREA = new Area(2985, 3241, 2988, 3238, 0);
        if (!WalkingHelper.walkToArea(RIMMINGTON_CLAY_ROCKS_AREA)) {
            return Timing.getSleepDelay();
        }

        GameObject rock = GameObjects.closest(g ->
                RIMMINGTON_CLAY_ROCKS_AREA.contains(g) &&
                        Arrays.stream(new int[]{11362, 11363}).anyMatch(i -> i == g.getID()));
        if (rock != null && rock.exists() && Interaction.delayEntityInteract(rock, "Mine")) {
            int count = Inventory.getEmptySlots();
            Sleep.sleepUntil(() -> Inventory.getEmptySlots() != count, () -> Players.getLocal().isAnimating(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
