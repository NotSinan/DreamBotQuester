package org.dreambot.quests.freequests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToCookLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 1) &&
                Inventory.containsAll("Pot of flour", "Egg", "Bucket of milk");
    }


    @Override
    public int onLoop() {
        final Area COOK_AREA = new Area(3205, 3217, 3212, 3212, 0);
        final String COOK_NAME = "Cook";
        final String[] DIALOGUE_OPTIONS = {"I'll get right on it."};
        return QuestHelper.goAndTalkToNpc(COOK_AREA, COOK_NAME, DIALOGUE_OPTIONS);
    }
}
