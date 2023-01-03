package org.dreambot.quests.freequests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.NPCHelper;

public class TalkToAuburyLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 3 && Inventory.contains("Research package") ||
                PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 4 ||
                PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 5 && !Inventory.contains("Research notes");
    }

    @Override
    public int onLoop() {
        final Area AUBURY_AREA = new Area(3250, 3402, 3255, 3400);
        final String AUBURY_NAME = "Aubury";
        final String[] DIALOGUE_OPTIONS = {"I've been sent here with a package for you."};
        return NPCHelper.goAndTalkToNpc(AUBURY_AREA, AUBURY_NAME, DIALOGUE_OPTIONS);
    }
}
