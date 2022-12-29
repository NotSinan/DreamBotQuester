package org.dreambot.quests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToBartenderLeaf extends Leaf {

    private final Area BARTENDER_AREA = new Area(3216, 3404, 3227, 3392);
    private final String[] DIALOGUE_OPTIONS = {"A glass of your finest ale please."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 &&
                Inventory.containsAll("Coins", "Garlic") && !Inventory.contains("Beer");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(BARTENDER_AREA, "Bartender", DIALOGUE_OPTIONS);
    }
}
