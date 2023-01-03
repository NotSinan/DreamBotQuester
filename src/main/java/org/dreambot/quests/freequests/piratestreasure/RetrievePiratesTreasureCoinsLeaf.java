package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.piratestreasure.smugglerum.SmuggleState;
import org.dreambot.utilities.QuestHelper;

public class RetrievePiratesTreasureCoinsLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 0 && !Inventory.contains("Coins")) ||
                (PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 1 && !SmuggleState.stashedRum && !Inventory.contains("Coins") && !Inventory.contains("Karamjan rum"));
    }

    @Override
    public int onLoop() {
        return QuestHelper.withdrawFromBank("Coins", 1000);
    }
}
