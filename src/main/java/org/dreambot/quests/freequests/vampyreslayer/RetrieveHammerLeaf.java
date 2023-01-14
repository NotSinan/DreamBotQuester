package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.OwnedItems;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.BankHelper;
import org.dreambot.utilities.helpers.WalkingHelper;
import org.dreambot.utilities.helpers.ironman.IronmanRetrieval;

public class RetrieveHammerLeaf extends Leaf {

    @Override
    public boolean isValid() {
        // 🐰🐰🐰 Check if there is no hammer in the inventory
        return !Inventory.contains("Hammer");
    }

    @Override
    public int onLoop() {
        return IronmanRetrieval.retrieveHammer();
    }
}
