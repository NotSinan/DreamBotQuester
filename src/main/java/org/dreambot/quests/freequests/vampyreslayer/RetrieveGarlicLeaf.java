package org.dreambot.quests.freequests.vampyreslayer;

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

public class RetrieveGarlicLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return !Inventory.contains("Garlic");
    }

    @Override
    public int onLoop() {
        final Area GARLIC_AREA = new Area(3096, 3270, 3102, 3266, 1);

        if (!QuestHelper.walkToArea(GARLIC_AREA)) {
            return Timing.loopReturn();
        }

        GameObject cupboard = GameObjects.closest("Cupboard");
        if (cupboard != null) {
            if (cupboard.hasAction("Open")) {
                if (Interaction.delayEntityInteract(cupboard, "Open")) {
                    Sleep.sleepUntil(() -> !cupboard.exists(), () -> Players.getLocal().isMoving(), 3000, 100);
                }
                return Timing.loopReturn();
            }
            if (Interaction.delayEntityInteract(cupboard, "Search")) {
                Sleep.sleepUntil(() -> Inventory.contains("Garlic"), () -> Players.getLocal().isMoving(), 3000, 100);
            }
        }
        return Timing.loopReturn();
    }
}
