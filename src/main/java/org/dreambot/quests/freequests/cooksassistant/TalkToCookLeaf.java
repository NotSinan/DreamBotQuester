package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToCookLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) == 0 ||
                PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) == 1) &&
                Inventory.containsAll("Pot of flour", "Egg", "Bucket of milk");
    }


    @Override
    public int onLoop() {
        final Area COOK_AREA = new Area(3205, 3217, 3212, 3212, 0);
        final String COOK_NAME = "Cook";
        final String[] DIALOGUE_OPTIONS = {"I'll get right on it."};
        return NPCHelper.goAndTalkToNpc(COOK_AREA, COOK_NAME, DIALOGUE_OPTIONS);
    }
}
