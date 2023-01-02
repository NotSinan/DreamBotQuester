package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveDemonSlayerCoinsLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 0 && !Inventory.contains("Coins");
    }

    @Override
    public int onLoop() {
        return QuestHelper.withdrawFromBank("Coins", 100);
    }
}
