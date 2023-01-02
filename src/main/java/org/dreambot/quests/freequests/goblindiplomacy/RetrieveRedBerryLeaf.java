package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveRedBerryLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_GOBLIN_DIPLOMACY.getId()) == 0 && Inventory.count("Redberries") < 3;
    }

    @Override
    public int onLoop() {
        final Area RED_BERRY_BUSH_AREA = new Area(3266, 3375, 3279, 3365);
        final int count = Inventory.count("Redberries");
        return QuestHelper.goAndInteractWithGameObject(
                RED_BERRY_BUSH_AREA,
                "Redberry bush",
                "Pick-from",
                () -> Inventory.count("Redberries") != count);
    }
}
