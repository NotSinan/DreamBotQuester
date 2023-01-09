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

public class RetrieveCowMeatLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.DRUIDIC_RITUAL.getConfigID()) == 0 &&
                Inventory.contains("Raw rat meat", "Raw bear meat") &&
                !Inventory.contains("Raw beef");
    }

    @Override
    public int onLoop() {

        GroundItem rawRatMeat = GroundItems.closest("Raw beef");
        if (rawRatMeat != null && rawRatMeat.interact("Take")) {
            Sleep.sleepUntil(() -> Inventory.contains("Raw beef"), 3000);
        }

        return NPCHelper.goAndKillNpc(
                new Area(3253, 3265, 3259, 3255), //cow area
                "Cow"
        );
    }
}
