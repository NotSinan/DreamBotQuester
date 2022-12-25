package org.dreambot.quests.doricsquest;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToDoricLeaf extends Leaf {

    private final Area DORICS_QUEST_AREA = new Area(2950, 3452, 2953, 3449);
    private final String[] DIALOGUES_OPTIONS = {"I wanted to use your anvils.", "Yes, I will get you the materials.", "Yes."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DORICS_QUEST.getId()) == 0;
    }

    @Override
    public int onLoop() {
        QuestHelper.goAndTalkToNpc(DORICS_QUEST_AREA, "Doric", DIALOGUES_OPTIONS);
        return Timing.loopReturn();
    }
}
