package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToFatherAereckLeaf extends Leaf {

    private final Area FATHER_AERECK_AREA = new Area(3240, 3215, 3247, 3204);
    private final String FATHER_AERECK_NAME = "Father Aereck";
    private final String[] DIALOGUE_OPTIONS = { "I'm looking for a quest!", "Ok, let me help then.", "Yes." };
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 0;
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc (FATHER_AERECK_AREA, FATHER_AERECK_NAME, DIALOGUE_OPTIONS); }
}
