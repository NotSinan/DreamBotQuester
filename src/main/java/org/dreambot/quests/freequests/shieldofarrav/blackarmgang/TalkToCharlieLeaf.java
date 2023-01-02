package org.dreambot.quests.freequests.shieldofarrav.blackarmgang;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToCharlieLeaf extends Leaf {

    private final Area CHARLIE_AREA = new Area(3207, 3394, 3211, 3390);
    private final String[] DIALOGUE_OPTIONS = {"Is there anything down this alleyway?", "Do you think they would let me join?", "Yes"};
    private final String CHARLIE = "Charlie the Tramp";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_SHIELD_OF_ARRAV.getId()) == 0;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(CHARLIE_AREA, CHARLIE, DIALOGUE_OPTIONS);
    }
}
