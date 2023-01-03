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

public class PlaceRedCogLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return !CogState.placedRedCog && Inventory.contains("Red cog");
    }

    @Override
    public int onLoop() {
        if (new Area(2623, 9599, 2558, 9664).contains(Players.getLocal())) { //entire clocktower dungeon
            final Area CLOCKTOWER_DUNGEON_MIDDLE_LADDER = new Area(2565, 9645, 2573, 9637, 0);
            return QuestHelper.goAndInteractWithGameObject(
                    CLOCKTOWER_DUNGEON_MIDDLE_LADDER,
                    "Ladder",
                    "Climb-up",
                    () -> !CLOCKTOWER_DUNGEON_MIDDLE_LADDER.contains(Players.getLocal())
            );
        }

        if (!QuestHelper.walkToArea(new Area(2564, 3245, 2573, 3239, 0))) { // clocktower middle room
            return Timing.getSleepDelay();
        }

        GameObject redSpindle = GameObjects.closest(g -> g.getName().equals("Clock spindle") && g.getTile().equals(new Tile(2568, 3243, 0)));
        Item redCog = Inventory.get("Red cog");
        if (redSpindle != null && redSpindle.exists() && redCog != null && redCog.isValid() && Interaction.delayUseItemOn(redCog, redSpindle)) {
            Sleep.sleepUntil(() -> !Inventory.contains("Red cog"),
                    () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
