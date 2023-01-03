package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.WalkingHelper;

public class ClueStepFourLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.X_MARKS_THE_SPOT.getVarBitID()) == 5 && Inventory.contains("Treasure scroll");
    }

    @Override
    public int onLoop() {
        final Area CLUE_FOUR_AREA = new Area(3075, 3261, 3079, 3259);

        if (!WalkingHelper.walkToArea(CLUE_FOUR_AREA)) {
            return Timing.getSleepDelay();
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
