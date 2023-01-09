package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.OwnedItems;
import org.dreambot.utilities.helpers.BankHelper;

public class RetrieveOwnedStakeLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return OwnedItems.contains("Stake") && !Inventory.contains("Stake");
    }

    @Override
    public int onLoop() {
        return BankHelper.withdrawFromBank("Stake", 1);
    }
}
