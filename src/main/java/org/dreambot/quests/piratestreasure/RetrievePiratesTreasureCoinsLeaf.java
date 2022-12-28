package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.piratestreasure.smugglerum.SmuggleState;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrievePiratesTreasureCoinsLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 0 && !Inventory.contains("Coins")) ||
                (PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 1 && !SmuggleState.stashedRum && !Inventory.contains("Coins") && !Inventory.contains("Karamjan rum"));
    }

    @Override
    public int onLoop() {
        return QuestHelper.withdrawFromBank("Coins", 1000);
    }
}