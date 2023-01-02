package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.CurseState;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToGnocciLeaf extends Leaf {
    private final Area GNOCCI_AREA = new Area(2543, 2864, 2547, 2860, 1);
    private final String[] DIALOGUE_OPTIONS = {"I hear you've been cursed."};

    @Override
    public boolean isValid() {
        return !CurseState.talkedToGnocci() ||
                (CurseState.talkedToIthoi() &&
                        CurseState.returnedToothpick() &&
                        CurseState.lookedThroughTelescope() &&
                        CurseState.foundDoll() &&
                        TalkToTessLeaf.leaveTessArea());
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(GNOCCI_AREA, "Gnocci the Cook", DIALOGUE_OPTIONS); }

}
