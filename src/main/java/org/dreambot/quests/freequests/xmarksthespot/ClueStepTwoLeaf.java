package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class ClueStepTwoLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 3 && Inventory.contains("Treasure scroll");
    }

    @Override
    public int onLoop() {
        final Area CLUE_TWO_AREA = new Area(3203, 3213, 3203, 3213);

        if (!QuestHelper.walkToArea(CLUE_TWO_AREA)) {
            return Timing.loopReturn();
        }

        Timing.sleepForDelay();
        if (Inventory.interact("Spade", "Dig")) {
            if (Sleep.sleepUntil(() -> Players.getLocal().isAnimating(), 3000)) {
                Sleep.sleepUntil(() -> !Players.getLocal().isAnimating(), 3000);
            }
        }

        return Timing.loopReturn();
    }
}
