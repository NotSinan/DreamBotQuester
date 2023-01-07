package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class MixBlueGoblinMailLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.containsAll("Blue dye", "Goblin mail");
    }

    @Override
    public int onLoop() {
        if (Inventory.containsAll("Blue dye", "Goblin mail")) {
            Item blueDye = Inventory.get("Blue dye");
            Item goblinMail = Inventory.get("Goblin mail");
            Interaction.delayUseItemOn(blueDye, goblinMail);
            Sleep.sleepUntil(() -> Inventory.contains("Blue goblin mail"), 3000);
        }
        return Timing.loopReturn();
    }
}
