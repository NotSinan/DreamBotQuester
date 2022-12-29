package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveGoblinMailLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_GOBLIN_DIPLOMACY.getId()) == 1 && Inventory.count("Goblin mail") < 3;
    }

    @Override
    public int onLoop() {
        return 0;
    }
}
