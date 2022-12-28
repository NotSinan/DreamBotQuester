package org.dreambot.quests.monksfriend;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToBrotherOmadLeaf extends Leaf {

    private final Area BROTHER_OMAD_AREA = new Area(2602, 3211, 2610, 3207);
    private final String[] DIALOGUE_OPTIONS = {"Why can't you sleep, what's wrong?", "Can I help at all?", "Yes."};
    private final String BROTHER_OMAD = "Brother Omad";


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_MONKS_FRIEND.getId()) == 0;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(BROTHER_OMAD_AREA, BROTHER_OMAD, DIALOGUE_OPTIONS);
    }
}