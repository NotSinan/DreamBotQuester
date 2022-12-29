package org.dreambot.quests.clocktower;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToBrotherKojoLeaf extends Leaf {

    private final Area BROTHER_KOJO_AREA = new Area(2563, 3255, 2574, 3239);
    private final String[] DIALOGUE_OPTIONS = {"OK old monk, what can I do?", "Yes."};
    private final String BROTHER_KOJO = "Brother Kojo";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_CLOCK_TOWER.getId()) == 0;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(BROTHER_KOJO_AREA, BROTHER_KOJO, DIALOGUE_OPTIONS);
    }
}
