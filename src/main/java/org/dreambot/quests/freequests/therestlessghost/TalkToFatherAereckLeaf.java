package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToFatherAereckLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 0;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc (
                new Area(3240, 3215, 3247, 3204),
                "Father Aereck",
                new String[]{ "I'm looking for a quest!", "Ok, let me help then.", "Yes." }
        );
    }
}
