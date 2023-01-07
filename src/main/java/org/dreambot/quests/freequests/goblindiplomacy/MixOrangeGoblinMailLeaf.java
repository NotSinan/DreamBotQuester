package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class MixOrangeGoblinMailLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.containsAll("Orange dye", "Goblin mail");
    }

    @Override
    public int onLoop() {
        if (Inventory.containsAll("Orange dye", "Goblin mail")) {
            Item orangeDye = Inventory.get("Orange dye");
            Item goblinMail = Inventory.get("Goblin mail");
            Interaction.delayUseItemOn(orangeDye, goblinMail);
            Sleep.sleepUntil(() -> Inventory.contains("Orange goblin mail"), 3000);
        }
        return Timing.loopReturn();
    }
}
