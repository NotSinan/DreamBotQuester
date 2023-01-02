package org.dreambot.quests.paidquests.clocktower.placecogs;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class RetrieveRedCogLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return !CogState.placedRedCog && !Inventory.contains("Red cog");
    }

    @Override
    public int onLoop() {
        if (!new Area(2623, 9599, 2558, 9664).contains(Players.getLocal())) { //entire clocktower dungeon
            final Area CLOCKTOWER_MIDDLE_ROOM = new Area(2564, 3245, 2573, 3239, 0);

            return QuestHelper.goAndInteractWithGameObject(
                    CLOCKTOWER_MIDDLE_ROOM,
                    "Ladder",
                    "Climb-down",
                    () -> !CLOCKTOWER_MIDDLE_ROOM.contains(Players.getLocal())
            );
        }

        return QuestHelper.pickupGroundSpawn(new Tile(2583, 9613, 0), "Red cog");
    }
}
