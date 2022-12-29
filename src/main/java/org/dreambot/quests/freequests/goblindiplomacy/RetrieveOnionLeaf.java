package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveOnionLeaf extends Leaf {

    private final Area ONION_AREA = new Area(3186, 3269, 3191, 3265);
    private final String GAME_OBJECT = "Onion";
    private final String ACTION = "Pick";
    private int count = 0;

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_GOBLIN_DIPLOMACY.getId()) == 0 && Inventory.count("Onion") != 2;
    }

    @Override
    public int onLoop() {
        count++;
        return QuestHelper.goAndInteractWithGameObject(ONION_AREA, GAME_OBJECT, ACTION, () -> Inventory.count("Onion") == count);
    }
}
