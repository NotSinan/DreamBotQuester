package org.dreambot.quests.impcatcher;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class GiveBeadsLeaf extends Leaf {

    private final Area MIZGOG_AREA = new Area(3099, 3166, 3107, 3159, 2);
    private final String[] DIALOGUE_OPTIONS = {"Give me a quest please.", "Yes."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_IMP_CATCHER.getId()) == 0 &&
                Inventory.contains("Black bead", "Red bead", "White bead", "Yellow bead") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_IMP_CATCHER.getId()) == 1 &&
                        Inventory.contains("Black bead", "Red bead", "White bead", "Yellow bead");
    }

    @Override
    public int onLoop() {
        QuestHelper.goAndTalkToNpc(MIZGOG_AREA, "Wizard Mizgog", DIALOGUE_OPTIONS);
        return Timing.loopReturn();
    }
}
