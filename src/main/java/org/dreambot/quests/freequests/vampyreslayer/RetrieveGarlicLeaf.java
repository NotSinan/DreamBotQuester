package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.ironman.IronmanRetrieval;

public class RetrieveGarlicLeaf extends Leaf {


    @Override
    public boolean isValid() {
        // 🐰🐰🐰 Check if there is no Garlic in the inventory
        return !Inventory.contains("Garlic");
    }

    @Override
    public int onLoop() {
        return IronmanRetrieval.retrieveGarlic();
    }
}
