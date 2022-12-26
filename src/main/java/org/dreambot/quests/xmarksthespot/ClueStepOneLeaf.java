package org.dreambot.quests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class ClueStepOneLeaf extends Leaf {

    private final Area CLUE_ONE_AREA = new Area(3230, 3209, 3230, 3209);

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 2 && Inventory.contains("Treasure scroll");
    }

    @Override
    public int onLoop() {

        if (!CLUE_ONE_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(CLUE_ONE_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (Inventory.interact("Spade", "Dig")) {
            Sleep.sleepUntil(() -> !Players.getLocal().isAnimating(), 3000);
        }
        return Timing.loopReturn();
    }
}
