package org.dreambot.quests.freequests.shieldofarrav.blackarmgang;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToKatrineLeaf extends Leaf {

    private final Area KATRINE_AREA = new Area(3182, 3387, 3189, 3382);
    private final String[] DIALOGUE_OPTIONS = {
            "I've heard you're the Black Arm Gang.",
            "I'd rather not reveal my sources.",
            "I want to become a member of your gang.",
            "Well, you can give me a try can't you?",
            "Ok, no problem."
    };
    private final String KATRINE = "Katrine";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_SHIELD_OF_ARRAV.getId()) == 1;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(KATRINE_AREA, KATRINE, DIALOGUE_OPTIONS);
    }
}
