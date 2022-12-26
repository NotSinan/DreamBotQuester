package org.dreambot.quests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class ClueStepTwoLeaf extends Leaf {

    public final Area CLUE_TWO_AREA = new Area(3203, 3213, 3203, 3213);
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 3 && Inventory.contains("Treasure scroll");
    }

    @Override
    public int onLoop() {

        if (!CLUE_TWO_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(CLUE_TWO_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (Inventory.contains("Spade")) {
            if (Inventory.interact("Spade", "Dig")) {
                Sleep.sleepUntil(() -> !Players.getLocal().isAnimating(), 3000);
            }
        }
        return Timing.loopReturn();
    }
}
