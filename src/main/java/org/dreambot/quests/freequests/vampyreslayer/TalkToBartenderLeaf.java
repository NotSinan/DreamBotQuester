package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToBartenderLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 && !Inventory.contains("Beer") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 2 && !Inventory.contains("Stake") && !Inventory.contains("Beer");
    }

    @Override
    public int onLoop() {
        final Area BARTENDER_AREA = new Area(3216, 3404, 3227, 3392);
        final String[] DIALOGUE_OPTIONS = {"A glass of your finest ale please."};
        return QuestHelper.goAndTalkToNpc(BARTENDER_AREA, "Bartender", DIALOGUE_OPTIONS);
    }
}
