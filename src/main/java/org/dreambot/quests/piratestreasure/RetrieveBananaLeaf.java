package org.dreambot.quests.piratestreasure;

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

public class RetrieveBananaLeaf extends Leaf {

    private final Area KARAMJA_BANANA_PLANTATION_AREA = new Area(2918, 3165, 2926, 3158);
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 1 &&
                Inventory.contains("Coins") &&
                Inventory.contains("Karamjan rum") &&
                Inventory.count("Banana") != 10;
    }

    @Override
    public int onLoop() {
        if (!KARAMJA_BANANA_PLANTATION_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(KARAMJA_BANANA_PLANTATION_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GameObject bananaTree = GameObjects.closest("Banana Tree");
        if (bananaTree != null && bananaTree.interact("Pick")) {
            Sleep.sleep(200, 300);
        }
        return Timing.loopReturn();
    }
}
