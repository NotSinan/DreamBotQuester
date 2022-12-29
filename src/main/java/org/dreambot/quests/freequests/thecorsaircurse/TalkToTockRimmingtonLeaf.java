package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToTockRimmingtonLeaf extends Leaf {
    private final Area CAPTAIN_TOCK_AREA = new Area(2906, 3227, 2915, 3225, 0);
    private final String[] DIALOGUE_OPTIONS = {"Okay, I'm ready go to Corsair Cove."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 5;
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(CAPTAIN_TOCK_AREA, "Captain Tock", DIALOGUE_OPTIONS); }

}
