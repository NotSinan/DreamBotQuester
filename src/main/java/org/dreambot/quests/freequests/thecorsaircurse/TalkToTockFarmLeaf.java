package org.dreambot.quests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToTockFarmLeaf extends Leaf {
    private final Area CAPTAIN_TOCK_AREA = new Area(3028, 3275, 3033, 3270, 0);
    private final String[] DIALOGUE_OPTIONS = {"What kind of help do you need?","Sure, I'll try to help with your curse.","Yes."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 5;
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(CAPTAIN_TOCK_AREA, "Captain Tock", DIALOGUE_OPTIONS); }

}
