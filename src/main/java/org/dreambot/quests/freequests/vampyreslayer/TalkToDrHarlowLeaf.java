package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToDrHarlowLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 2 && !Inventory.contains("Stake");
    }

    @Override
    public int onLoop() {
        final Area DR_HARLOW_AREA = new Area(3216, 3404, 3227, 3392);
        final String[] DIALOGUE_OPTIONS = {"Morgan needs your help!", "Okay mate."};

        return QuestHelper.goAndTalkToNpc(DR_HARLOW_AREA, "Dr Harlow", DIALOGUE_OPTIONS);
    }
}
