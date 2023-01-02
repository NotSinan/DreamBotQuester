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

public class RetrieveCopperLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return Inventory.count("Copper ore") < 4;
    }

    @Override
    public int onLoop() {
        final Area RIMMINGTON_COPPER_ROCKS_AREA = new Area(2975, 3249, 2980, 3246, 0);
        if (!QuestHelper.walkToArea(RIMMINGTON_COPPER_ROCKS_AREA)) {
            return Timing.loopReturn();
        }
        GameObject rock = GameObjects.closest(g -> RIMMINGTON_COPPER_ROCKS_AREA.contains(g) && Arrays.stream(new int[]{11161, 10943}).anyMatch(i -> i == g.getID()));
        if (rock != null && rock.exists() && Interaction.delayEntityInteract(rock, "Mine")) {
            int count = Inventory.getEmptySlots();
            Sleep.sleepUntil(() -> Inventory.getEmptySlots() != count, () -> Players.getLocal().isAnimating(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
