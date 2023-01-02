package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.CurseState;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class ObserveIthoiTelescopeLeaf extends Leaf {
    private final Area ITHOI_AREA = new Area(2527, 2841, 2532, 2835, 1);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 15 &&
                CurseState.talkedToIthoi() &&
                CurseState.returnedToothpick() &&
                CurseState.talkedToColin() &&
                CurseState.foundDoll() &&
                TalkToTessLeaf.leaveTessArea();
    }

    @Override
    public int onLoop() { return QuestHelper.goAndInteractWithGameObject(ITHOI_AREA, "Telescope", "Observe", () -> Dialogues.inDialogue()); }

}
