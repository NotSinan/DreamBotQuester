package org.dreambot.quests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveCowMeatLeaf extends Leaf {

    private final Area COW_AREA = new Area(3253, 3265, 3259, 3255);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) == 0 &&
                Inventory.contains("Raw rat meat") &&
                !Inventory.contains("Raw beef");
    }

    @Override
    public int onLoop() {
        QuestHelper.goAndKillNpc(COW_AREA, "Cow");

        GroundItem rawRatMeat = GroundItems.closest("Raw beef");
        if (rawRatMeat != null && rawRatMeat.interact("Take")) {
            Sleep.sleepUntil(() -> Inventory.contains("Raw beef"), 3000);
        }

        return Timing.loopReturn();
    }
}
