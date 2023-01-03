package org.dreambot.quests.paidquests.clocktower.placecogs;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class DropExtraCogsLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return API.lastGameMessage.contains("The cogs are too heavy to carry more than one at a time.");
    }

    @Override
    public int onLoop() {
        Inventory.dropAll(i -> i.getName().endsWith(" cog"));
        API.lastGameMessageTile = null;
        API.lastGameMessage = "";
        return Timing.getSleepDelay();
    }
}
