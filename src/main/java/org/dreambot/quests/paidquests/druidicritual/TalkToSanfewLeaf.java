package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToSanfewLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) == 1 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) == 2 &&
                        Inventory.contains("Enchanted rat", "Enchanted beef", "Enchanted chicken", "Enchanted bear");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(2894, 3431, 2901, 3425, 1), //sanfew area
                "Sanfew",
                new String[]{"I've been sent to help purify the Varrock stone circle."});
    }
}
