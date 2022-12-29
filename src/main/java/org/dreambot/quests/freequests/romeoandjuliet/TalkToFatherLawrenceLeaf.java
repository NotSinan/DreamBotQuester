package org.dreambot.quests.freequests.romeoandjuliet;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToFatherLawrenceLeaf extends Leaf {
    private final Area FATHER_LAWRENCE_AREA = new Area(3252, 3488, 3255, 3482);
    private final String[] DIALOGUE_OPTIONS = {"Ok, thanks."};


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 30;
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(FATHER_LAWRENCE_AREA, "Father Lawrence", DIALOGUE_OPTIONS); }
}
