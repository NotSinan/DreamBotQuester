package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToKaqemeexLeaf extends Leaf {

    private final Area KAQEMEEX_AREA = new Area(2914, 3490, 2931, 3478);
    private final String[] DIALOGUE_OPTIONS = {"I'm in search of a quest.", "Okay, I will try and help.", "Yes."};
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) == 0 &&
                Inventory.contains("Raw rat meat", "Raw beef", "Raw chicken", "Raw bear meat") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) == 3;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(KAQEMEEX_AREA, "Kaqemeex", DIALOGUE_OPTIONS);
    }
}
