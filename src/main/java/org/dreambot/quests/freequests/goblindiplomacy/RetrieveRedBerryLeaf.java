package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveRedBerryLeaf extends Leaf {

    private final Area RED_BERRY_BUSH_AREA = new Area(3266, 3375, 3279, 3365);
    private final String GAME_OBJECT = "Redberry bush";
    private final String ACTION = "Pick-from";
    private int count = 0;

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_GOBLIN_DIPLOMACY.getId()) == 0 && Inventory.count("Redberries") != 3;
    }

    @Override
    public int onLoop() {
        count++;
        return QuestHelper.goAndInteractWithGameObject(RED_BERRY_BUSH_AREA, GAME_OBJECT, ACTION, () -> Inventory.count("Redberries") == count);
    }
}
