package org.dreambot.quests.freequests.therestlessghost;

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
import org.dreambot.utilities.helpers.WalkingHelper;

public class ReturnSkullToGhostLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) == 4 && Inventory.contains("Ghost's skull");
    }

    @Override
    public int onLoop() {
        if (!WalkingHelper.walkToArea(new Area(3247, 3195, 3252, 3190))) { //ghost area
            return Timing.getSleepDelay();
        }

        GameObject coffin = GameObjects.closest("Coffin");
        if (coffin != null && coffin.exists()) {
            if (coffin.hasAction("Open")) {
                if (Interaction.delayEntityInteract(coffin, "Open")) {
                    Sleep.sleepUntil(() -> !coffin.exists(), () -> Players.getLocal().isMoving(), 3000, 100);
                }
                return Timing.loopReturn();
            }
            Item skull = Inventory.get("Ghost's skull");
            if (skull != null && skull.isValid() && Interaction.delayUseItemOn(skull, coffin)) {
                Sleep.sleepUntil(() -> !Inventory.contains("Ghost's skull"), 3000);
            }
        }
        return Timing.loopReturn();
    }
}
