package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class MixDyeLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.containsAll("Yellow dye", "Red dye");
    }

    @Override
    public int onLoop() {

        if (Inventory.contains("Red dye") && Inventory.contains("Yellow dye")) {
            Item redDye = Inventory.get("Red dye");
            Item yellowDye = Inventory.get("Yellow dye");
            Interaction.delayUseItemOn(redDye, yellowDye);
            Sleep.sleepUntil(() -> Inventory.contains("Orange dye"), 3000);
        }
        return Timing.loopReturn();
    }
}
