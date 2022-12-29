package org.dreambot.quests.clocktower;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveBlueCogLeaf extends Leaf {

    private final Area AREA = new Area(
            new Tile(2619, 9660, 0),
            new Tile(2619, 9662, 0),
            new Tile(2622, 9662, 0),
            new Tile(2622, 9660, 0));


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_CLOCK_TOWER.getId()) == 1 && !Inventory.contains("Blue cog");
    }

    @Override
    public int onLoop() {

        if (!AREA.contains(Players.getLocal())) {
            Walking.walk(AREA.getRandomTile());
        }
        return Timing.loopReturn();
    }
}
