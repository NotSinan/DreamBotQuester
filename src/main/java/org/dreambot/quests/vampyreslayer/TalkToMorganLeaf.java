package org.dreambot.quests.vampyreslayer;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToMorganLeaf extends Leaf {

    private final Area MORGAN_AREA = new Area(3096, 3270, 3102, 3266);
    private final String[] DIALOGUE_OPTIONS = {"Yes."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 0;
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(MORGAN_AREA, "Morgan", DIALOGUE_OPTIONS); }
}
