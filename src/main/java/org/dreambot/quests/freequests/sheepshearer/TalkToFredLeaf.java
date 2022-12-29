package org.dreambot.quests.sheepshearer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToFredLeaf extends Leaf {

    private final Area FARMER_FRED_FARM_AREA = new Area(3183, 3280, 3192, 3270);
    private final String FRED_NAME = "Fred the Farmer";
    private final String[] DIALOGUE_OPTIONS = { "I'm looking for a quest.", "Yes, okay. I can do that.", "Yes.", "I need to talk to you about shearing these sheep!" };
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(179) == 0 ||
                PlayerSettings.getConfig(179) == 1 && Inventory.count("Ball of wool") >= 20 ||
                PlayerSettings.getConfig(179) == 20;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(FARMER_FRED_FARM_AREA, FRED_NAME, DIALOGUE_OPTIONS);
    }
}
