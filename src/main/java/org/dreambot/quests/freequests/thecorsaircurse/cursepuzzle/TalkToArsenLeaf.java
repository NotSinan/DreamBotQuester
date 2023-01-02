package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.CurseState;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class TalkToArsenLeaf extends Leaf {
    private final Area ARSEN_COLIN_AREA = new Area(2553, 2859, 2559, 2853, 1);
    private final String[] DIALOGUE_OPTIONS = {"I hear you've been cursed."};
    private final Area CHIEF_TESS_AREA = new Area(1998, 9012, 2026, 8993, 1);
    private final Tile LADDER_TILE = new Tile(2012, 9005, 1);

    @Override
    public boolean isValid() {
        return !CurseState.talkedToArsen() ||
                (CurseState.talkedToIthoi() &&
                        CurseState.returnedToothpick() &&
                        CurseState.lookedThroughTelescope() &&
                        CurseState.finishedGnocci() &&
                        TalkToTessLeaf.leaveTessArea());
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(ARSEN_COLIN_AREA, "Arsen the Thief", DIALOGUE_OPTIONS); }

}
