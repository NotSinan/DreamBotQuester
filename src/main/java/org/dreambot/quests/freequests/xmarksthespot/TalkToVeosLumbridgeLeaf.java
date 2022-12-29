package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToVeosLumbridgeLeaf extends Leaf {

    private final Area VEOS_AREA = new Area(3226, 3242, 3233, 3239);
    private final String VEOS_NAME = "Veos";
    private final String[] DIALOGUE_OPTIONS = {"I'm looking for a quest.", "Sounds good, what should I do?", "Okay, thanks Veos.", "Yes.", "Can I help?"};
    @Override
    public boolean isValid() {
        return Inventory.contains("Spade") && PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 0
                || PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 1
                || PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 2 && !Inventory.contains("Treasure scroll");
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(VEOS_AREA, VEOS_NAME, DIALOGUE_OPTIONS); }
}
