package org.dreambot.quests.freequests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToLumbridgeDukeLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 0 ||
                PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) == 1 && !Inventory.contains("Air talisman");
    }

    @Override
    public int onLoop() {
        final Area LUMBRIDGE_DUKE_AREA = new Area(3208, 3225, 3213, 3218, 1);
        final String DUKE_NAME = "Duke Horacio";
        final String[] DIALOGUE_OPTIONS = {"Have you any quests for me?", "Sure, no problem", "Yes."};
        return NPCHelper.goAndTalkToNpc(LUMBRIDGE_DUKE_AREA, DUKE_NAME, DIALOGUE_OPTIONS);
    }
}
