package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

/**
 * This class withdraws 100 coins from the bank.
 */
public class RetrieveDemonSlayerCoinsLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 0 && !Inventory.contains("Coins");
    }

    @Override
    public int onLoop() {
        return QuestHelper.withdrawFromBank("Coins", 100);
    }
}
