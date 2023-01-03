package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToMorganLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.VAMPIRE_SLAYER.getConfigID()) == 0;
    }

    @Override
    public int onLoop() {
        final Area MORGAN_AREA = new Area(3096, 3270, 3102, 3266);
        final String[] DIALOGUE_OPTIONS = {"Yes."};

        return QuestHelper.goAndTalkToNpc(MORGAN_AREA, "Morgan", DIALOGUE_OPTIONS);
    }
}
