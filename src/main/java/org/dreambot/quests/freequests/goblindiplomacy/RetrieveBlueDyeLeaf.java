package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class RetrieveBlueDyeLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 0 &&
                Inventory.count("Woad leaf") >= 2 &&
                !Inventory.contains("Blue dye") &&
                !Inventory.contains("Blue goblin mail");
    }

    @Override
    public int onLoop() {
        Area AGGIE_AREA = new Area(3083, 3261, 3088, 3256);
        final String[] DIALOGUE_OPTIONS = {
                "Can you make dyes for me please?",
                "What do you need to make blue dye?",
                "Okay, make me some blue dye please."
        };
        return NPCHelper.goAndTalkToNpc(AGGIE_AREA, "Aggie", DIALOGUE_OPTIONS);
    }
}
