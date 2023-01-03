package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;

public class RetrieveGoblinMailLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 1 && Inventory.count("Goblin mail") < 3;
    }

    @Override
    public int onLoop() {
        return 0;
    }
}
