package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToFatherAereckLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) == 0;
    }

    @Override
    public int onLoop() {
        return NPCHelper.goAndTalkToNpc(
                new Area(3240, 3215, 3247, 3204),
                "Father Aereck",
                new String[]{"I'm looking for a quest!", "Ok, let me help then.", "Yes."}
        );
    }
}
