package org.dreambot.quests.freequests.romeoandjuliet;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToFatherLawrenceLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.ROMEO_AND_JULIET.getConfigID()) == 30;
    }

    @Override
    public int onLoop() {
        final Area FATHER_LAWRENCE_AREA = new Area(3252, 3488, 3255, 3482);
        final String[] DIALOGUE_OPTIONS = {"Ok, thanks."};
        return NPCHelper.goAndTalkToNpc(FATHER_LAWRENCE_AREA, "Father Lawrence", DIALOGUE_OPTIONS);
    }
}
