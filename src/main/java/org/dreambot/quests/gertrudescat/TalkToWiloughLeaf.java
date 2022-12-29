package org.dreambot.quests.gertrudescat;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToWiloughLeaf extends Leaf {

    private final Area WILOUGH_AREA = new Area(3213, 3437, 3223, 3429);
    private final String[] DIALOGUE_OPTIONS = {"What will make you tell me?", "Okay then, I'll pay."};
    private final String WILOUGH = "Wilough";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 1;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(WILOUGH_AREA, WILOUGH, DIALOGUE_OPTIONS);
    }
}
