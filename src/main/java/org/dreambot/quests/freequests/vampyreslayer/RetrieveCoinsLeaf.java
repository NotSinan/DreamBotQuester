package org.dreambot.quests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveCoinsLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 &&
                Inventory.count("Coins") < 100 &&
                Inventory.contains("Garlic");
    }

    @Override
    public int onLoop() { return QuestHelper.withdrawFromBank("Coins", 100); }
}
