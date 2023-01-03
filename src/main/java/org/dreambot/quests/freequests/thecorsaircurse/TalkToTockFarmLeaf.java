package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToTockFarmLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 0 ||
                PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 5;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(3028, 3275, 3033, 3270, 0),
                "Captain Tock",
                new String[]{"What kind of help do you need?", "Sure, I'll try to help with your curse.", "Yes."}
        );
    }
}
