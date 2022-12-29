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

public class RetrieveRatMeatLeaf extends Leaf {

    private final Area RAT_AREA = new Area(3191, 3211, 3199, 3204);
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) == 0 &&
                Inventory.contains("Raw bear meat") && !Inventory.contains("Raw rat meat");
    }

    @Override
    public int onLoop() {

        GroundItem rawRatMeat = GroundItems.closest("Raw rat meat");
        if (rawRatMeat != null && rawRatMeat.interact("Take")) {
            Sleep.sleepUntil(() -> Inventory.contains("Raw rat meat"), 3000);
        }

        return QuestHelper.goAndKillNpc(RAT_AREA, "Giant rat");
    }
}
