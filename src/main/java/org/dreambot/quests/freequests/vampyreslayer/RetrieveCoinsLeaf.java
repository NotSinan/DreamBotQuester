package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class RetrieveCoinsLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.count("Coins") < 50;
    }

    @Override
    public int onLoop() {
        return QuestHelper.withdrawFromBank("Coins", 100);
    }
}
