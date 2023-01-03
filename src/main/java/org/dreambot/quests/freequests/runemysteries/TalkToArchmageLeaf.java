package org.dreambot.quests.freequests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.NPCHelper;

public class TalkToArchmageLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 1 && Inventory.contains("Air talisman") ||
                PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 2 ||
                PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 3 && !Inventory.contains("Research package") ||
                PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 5 && Inventory.contains("Research notes");
    }

    @Override
    public int onLoop() {
        final Area WIZARD_TOWER_AREA = new Area(3099, 9574, 3107, 9569);
        final String ARCHMAGE_SEDRIDOR_NAME = "Archmage Sedridor";
        final String[] DIALOGUE_OPTIONS = {"I'm looking for the head wizard.", "Okay, here you are.", "Go ahead.", "Yes, certainly."};
        return NPCHelper.goAndTalkToNpc(WIZARD_TOWER_AREA, ARCHMAGE_SEDRIDOR_NAME, DIALOGUE_OPTIONS);
    }
}
