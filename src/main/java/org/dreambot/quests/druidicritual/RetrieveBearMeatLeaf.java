package org.dreambot.quests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveBearMeatLeaf extends Leaf {

    private final Area BEAR_AREA = new Area(3070, 3612, 3094, 3597);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) == 0 &&
                !Inventory.contains("Raw bear meat");
    }

    @Override
    public int onLoop() {
        GroundItem rawRatMeat = GroundItems.closest("Raw bear meat");
        if (rawRatMeat != null && Interaction.delayEntityInteract(rawRatMeat, "Take")) {
            Sleep.sleepUntil(() -> Inventory.contains("Raw bear meat"), 3000);
        }
        return QuestHelper.goAndKillNpc(BEAR_AREA, "Grizzly bear");
    }
}
