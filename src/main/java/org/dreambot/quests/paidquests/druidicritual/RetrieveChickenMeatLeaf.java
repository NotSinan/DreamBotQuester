package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class RetrieveChickenMeatLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.DRUIDIC_RITUAL.getConfigID()) == 0 &&
                Inventory.contains("Raw rat meat", "Raw beef", "Raw bear meat") &&
                !Inventory.contains("Raw chicken");
    }

    @Override
    public int onLoop() {

        GroundItem rawRatMeat = GroundItems.closest("Raw chicken");
        if (rawRatMeat != null && rawRatMeat.interact("Take")) {
            Sleep.sleepUntil(() -> Inventory.contains("Raw chicken"), 3000);
        }

        return NPCHelper.goAndKillNpc(
                new Area(3227, 3300, 3232, 3296),
                "Chicken");
    }
}
