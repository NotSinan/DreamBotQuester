package org.dreambot.quests.vampyreslayer;

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

public class RetrieveGarlicLeaf extends Leaf {

    private final Area GARLIC_AREA = new Area(3096, 3270, 3102, 3266, 1);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 && !Inventory.contains("Garlic");
    }

    @Override
    public int onLoop() {
        if (!GARLIC_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(GARLIC_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GameObject cupboard = GameObjects.closest("Cupboard");
        if (cupboard != null && cupboard.hasAction("Open")) {
            cupboard.interact("Open");
            Sleep.sleepUntil(() -> cupboard.hasAction("Search"), 3000);
            return Timing.loopReturn();
        } else {
            cupboard.interact("Search");
            Sleep.sleepUntil(() -> Inventory.contains("Garlic"), 3000);
        }
        return 0;
    }
}
