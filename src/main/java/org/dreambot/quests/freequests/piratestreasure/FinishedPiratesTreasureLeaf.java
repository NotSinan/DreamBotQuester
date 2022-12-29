package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class FinishedPiratesTreasureLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) > 3;
    }

    @Override
    public int onLoop() {
        if(Inventory.contains("Casket")) {
            if(Inventory.interact("Casket", "Open")) {
                Sleep.sleepUntil(() -> Inventory.contains("Gold ring"), 3000);
            }
            return Timing.loopReturn();
        }

        if(Inventory.contains("Pirate message", "Chest key")) {
            if(Inventory.dropAll("Pirate message", "Chest key")) {
                Sleep.sleepUntil(() -> !Inventory.contains("Pirate message", "Chest key"), 3000);
            }
            return Timing.loopReturn();
        }

        Logger.log("Finished Pirate's Treasure, stopping script...!");
        return -1;
    }
}
