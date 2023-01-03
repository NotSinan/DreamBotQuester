package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class RetrieveRatMeatLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.DRUIDIC_RITUAL.getConfigID()) == 0 &&
                Inventory.contains("Raw bear meat") && !Inventory.contains("Raw rat meat");
    }

    @Override
    public int onLoop() {

        GroundItem rawRatMeat = GroundItems.closest("Raw rat meat");
        if (rawRatMeat != null && rawRatMeat.interact("Take")) {
            Sleep.sleepUntil(() -> Inventory.contains("Raw rat meat"), 3000);
        }

        return QuestHelper.goAndKillNpc(
                new Area(3191, 3211, 3199, 3204), //rat area
                "Giant rat"
        );
    }
}
