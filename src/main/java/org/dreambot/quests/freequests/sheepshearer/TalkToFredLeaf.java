package org.dreambot.quests.freequests.sheepshearer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToFredLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.SHEEP_SHEARER.getConfigID()) == 0 ||
                PlayerSettings.getConfig(FreeQuest.SHEEP_SHEARER.getConfigID()) == 1 && Inventory.count("Ball of wool") >= 20 ||
                PlayerSettings.getConfig(FreeQuest.SHEEP_SHEARER.getConfigID()) == 20;
    }

    @Override
    public int onLoop() {
        final Area FARMER_FRED_FARM_AREA = new Area(3183, 3280, 3192, 3270);
        final String FRED_NAME = "Fred the Farmer";
        final String[] DIALOGUE_OPTIONS = {"I'm looking for a quest.", "Yes, okay. I can do that.", "Yes.", "I need to talk to you about shearing these sheep!"};
        return QuestHelper.goAndTalkToNpc(FARMER_FRED_FARM_AREA, FRED_NAME, DIALOGUE_OPTIONS);
    }
}
