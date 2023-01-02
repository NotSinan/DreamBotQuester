package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.CurseState;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToIthoiLeaf extends Leaf {
    private final Area ITHOI_AREA = new Area(2527, 2841, 2532, 2835, 1);
    private final String[] DIALOGUE_OPTIONS = {"I hear you've been cursed."};

    @Override
    public boolean isValid() {
        return !CurseState.talkedToIthoi();
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(ITHOI_AREA, "Ithoi the Navigator", DIALOGUE_OPTIONS); }

}
