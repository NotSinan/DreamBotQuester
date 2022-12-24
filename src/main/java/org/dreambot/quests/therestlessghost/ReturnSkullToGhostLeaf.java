package org.dreambot.quests.therestlessghost;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class ReturnSkullToGhostLeaf extends Leaf {

    private final Area GHOST_AREA = new Area(3247, 3195, 3252, 3190);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 4 && Inventory.contains("Ghost's skull");
    }

    @Override
    public int onLoop() {
        if (!GHOST_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(GHOST_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GameObject coffin = GameObjects.closest("Coffin");
        if (coffin.hasAction("Open")) {
            coffin.interact("Open");
            return Timing.loopReturn();
        } else {
            Inventory.interact("Ghost's skull", "Use");
            Sleep.sleepUntil(() -> Inventory.getSelectedItemName().equals("Ghost's skull"), 3000);
            coffin.interact("Use");
        }

        return Timing.loopReturn();
    }
}
