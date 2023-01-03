package org.dreambot.quests.paidquests.clocktower.placecogs;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class RetrieveBlueCogLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.CLOCK_TOWER.getConfigID()) == 1 && !Inventory.contains("Blue cog");
    }

    @Override
    public int onLoop() {
        if (new Area(2571, 9633, 2575, 9630, 0).contains(Players.getLocal())) { //blue cog room
            return QuestHelper.pickupGroundSpawn(new Tile(2574, 9633, 0), "Blue cog");
        }

        final Area BLUE_COG_PRIOR_ROOM = new Area(2576, 9631, 2580, 9625, 0);
        if (BLUE_COG_PRIOR_ROOM.contains(Players.getLocal())) { //just before blue cog room
            GameObject wall = GameObjects.closest(g ->
                    g.getName().equals("Wall") &&
                            g.hasAction("Push") &&
                            g.getTile().equals(new Tile(2575, 9631, 0)));
            if (wall != null && wall.exists() && Interaction.delayEntityInteract(wall, "Push")) {
                Sleep.sleepUntil(() -> !BLUE_COG_PRIOR_ROOM.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 3000, 100);
            }
            return Timing.loopReturn();
        }

        if (!new Area(2623, 9599, 2558, 9664).contains(Players.getLocal())) { //entire clocktower dungeon
            final Area CLOCKTOWER_MIDDLE_ROOM = new Area(2564, 3245, 2573, 3239, 0);

            return QuestHelper.goAndInteractWithGameObject(
                    CLOCKTOWER_MIDDLE_ROOM,
                    "Ladder",
                    "Climb-down",
                    () -> !CLOCKTOWER_MIDDLE_ROOM.contains(Players.getLocal())
            );
        }

        Tile[] PATH_TO_BLUE_COG = {
                new Tile(2618, 9661, 0),
                new Tile(2613, 9652, 0),
                new Tile(2616, 9642, 0),
                new Tile(2615, 9630, 0),
                new Tile(2607, 9616, 0),
                new Tile(2597, 9602, 0),
                new Tile(2585, 9601, 0),
                new Tile(2580, 9603, 0),
                new Tile(2580, 9612, 0),
                new Tile(2580, 9625, 0),
                new Tile(2576, 9631, 0)
        };

        for (Tile tileOnBluePath : PATH_TO_BLUE_COG) {
            if (tileOnBluePath.canReach()) {
                QuestHelper.walkToTile(tileOnBluePath);
                return Timing.loopReturn();
            }
        }

        return Timing.loopReturn();
    }
}
