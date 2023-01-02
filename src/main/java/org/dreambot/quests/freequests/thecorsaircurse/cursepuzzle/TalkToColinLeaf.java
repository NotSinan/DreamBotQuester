package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.CurseState;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToColinLeaf extends Leaf {
    private final Area ARSEN_COLIN_AREA = new Area(2553, 2859, 2559, 2853, 1);
    private final String[] DIALOGUE_OPTIONS = {"I hear you've been cursed."};

    @Override
    public boolean isValid() {
        return !CurseState.talkedToColin() ||
                (CurseState.talkedToIthoi() &&
                        CurseState.finishedArsen() &&
                        CurseState.finishedGnocci() &&
                        CurseState.lookedThroughTelescope() &&
                        TalkToTessLeaf.leaveTessArea());
    }

    @Override
    public int onLoop() {

        return QuestHelper.goAndTalkToNpc(ARSEN_COLIN_AREA, "Cabin Boy Colin", DIALOGUE_OPTIONS); }

}
