package org.dreambot.quests.freequests.doricsquest;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToDoricLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DORICS_QUEST.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_DORICS_QUEST.getId()) == 10;
    }

    @Override
    public int onLoop() {
        final Area DORICS_QUEST_AREA = new Area(2950, 3454, 2953, 3449);
        final String[] DIALOGUES_OPTIONS = {"I wanted to use your anvils.", "Yes, I will get you the materials.", "Yes."};
        return QuestHelper.goAndTalkToNpc(DORICS_QUEST_AREA, "Doric", DIALOGUES_OPTIONS);
    }
}
