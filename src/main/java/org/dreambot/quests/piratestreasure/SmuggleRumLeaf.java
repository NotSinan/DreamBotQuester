package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;

public class SmuggleRumLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 1 &&
                Inventory.contains("Coins") &&
                Inventory.contains("Karamjan rum") &&
                Inventory.count("Banana") == 10;
    }

    @Override
    public int onLoop() {
        return 0;
    }
}
