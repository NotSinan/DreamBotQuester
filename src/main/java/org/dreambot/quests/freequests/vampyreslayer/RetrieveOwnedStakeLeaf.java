package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.OwnedItems;

public class RetrieveOwnedStakeLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return OwnedItems.contains("Stake") && !Inventory.contains("Stake");
    }

    @Override
    public int onLoop() {
        return QuestHelper.withdrawFromBank("Stake", 1);
    }
}
