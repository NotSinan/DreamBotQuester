package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToBartenderLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.VAMPIRE_SLAYER.getConfigID()) == 1 && !Inventory.contains("Beer") ||
                PlayerSettings.getConfig(FreeQuest.VAMPIRE_SLAYER.getConfigID()) == 2 && !Inventory.contains("Stake") && !Inventory.contains("Beer");
    }

    @Override
    public int onLoop() {
        final Area BARTENDER_AREA = new Area(3216, 3404, 3227, 3392);
        final String[] DIALOGUE_OPTIONS = {"A glass of your finest ale please."};
        return QuestHelper.goAndTalkToNpc(BARTENDER_AREA, "Bartender", DIALOGUE_OPTIONS);
    }
}
