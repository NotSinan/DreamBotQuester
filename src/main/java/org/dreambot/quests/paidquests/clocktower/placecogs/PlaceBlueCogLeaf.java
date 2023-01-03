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

public class PlaceBlueCogLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return !CogState.placedBlueCog && Inventory.contains("Blue cog");
    }

    @Override
    public int onLoop() {
        final Area CLOCKTOWER_FIRST_FLOOR = new Area(2564, 3245, 2573, 3239, 1);
        if (CLOCKTOWER_FIRST_FLOOR.contains(Players.getLocal())) {
            GameObject blueSpindle = GameObjects.closest(g -> g.hasAction("Clock spindle") && g.getTile().equals(new Tile(2569, 3240, 1)));
            Item blueCog = Inventory.get("Blue cog");
            if (blueSpindle != null && blueSpindle.exists() && blueCog != null && blueCog.isValid() &&
                    Interaction.delayUseItemOn(blueCog, blueSpindle)) {
                Sleep.sleepUntil(() -> !Inventory.contains("Blue cog"), () -> Players.getLocal().isMoving(), 3000, 100);
            }
            return Timing.loopReturn();
        }

        final Area BLUE_COG_ROOM = new Area(2571, 9633, 2575, 9630, 0);
        if (BLUE_COG_ROOM.contains(Players.getLocal())) {
            return QuestHelper.goAndInteractWithGameObject(BLUE_COG_ROOM, "Ladder", "Climb-up", () -> BLUE_COG_ROOM.contains(Players.getLocal()));
        }

        final Area BLUE_COG_PRIOR_ROOM = new Area(2576, 9631, 2580, 9625, 0);
        if (BLUE_COG_PRIOR_ROOM.contains(Players.getLocal())) {
            GameObject wall = GameObjects.closest(g ->
                    g.getName().equals("Wall") &&
                            g.hasAction("Push") &&
                            g.getTile().equals(new Tile(2575, 9631, 0)));
            if (wall != null && wall.exists() && Interaction.delayEntityInteract(wall, "Push")) {
                Sleep.sleepUntil(() -> !BLUE_COG_PRIOR_ROOM.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 3000, 100);
            }
            return Timing.loopReturn();
        }

        if (new Area(2623, 9599, 2558, 9664).contains(Players.getLocal())) { //entire clocktower dungeon
            final Area CLOCKTOWER_DUNGEON_MIDDLE_LADDER = new Area(2565, 9645, 2573, 9637, 0);
            return QuestHelper.goAndInteractWithGameObject(
                    CLOCKTOWER_DUNGEON_MIDDLE_LADDER,
                    "Ladder",
                    "Climb-up",
                    () -> !CLOCKTOWER_DUNGEON_MIDDLE_LADDER.contains(Players.getLocal())
            );
        }

        final Area GROUND_FLOOR_CLOCKTOWER = new Area(2564, 3245, 2573, 3239, 0);
        if (GROUND_FLOOR_CLOCKTOWER.contains(Players.getLocal())) {
            return QuestHelper.goAndInteractWithGameObject(
                    GROUND_FLOOR_CLOCKTOWER,
                    "Staircase",
                    "Climb-up",
                    () -> !GROUND_FLOOR_CLOCKTOWER.contains(Players.getLocal()));
        }


        return Timing.loopReturn();
    }
}
