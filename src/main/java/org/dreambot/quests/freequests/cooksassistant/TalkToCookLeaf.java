package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToCookLeaf extends Leaf {
    private final Area COOK_AREA = new Area(3205, 3217, 3212, 3212, 0);
    private final String COOK_NAME = "Cook";
    private final String[] DIALOGUE_OPTIONS = {"I'll get right on it."};
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 1;
    }


    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(COOK_AREA,COOK_NAME, DIALOGUE_OPTIONS);
    }
}
