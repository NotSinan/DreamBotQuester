package org.dreambot.quests.freequests.goblindiplomacy.retrieveitems;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RetrieveRedBerryLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 0 &&
                Inventory.count("Redberries") < 3 &&
                !Inventory.contains("Red dye") &&
                !Inventory.contains("Orange dye") &&
                !Inventory.contains("Orange goblin mail");
    }

    @Override
    public int onLoop() {
        final Area RED_BERRY_BUSH_AREA = new Area(3266, 3375, 3279, 3365);
        final int count = Inventory.count("Redberries");
        return GameObjectHelper.goAndInteractWithGameObject(RED_BERRY_BUSH_AREA, "Redberry bush", "Pick-from", () -> Inventory.count("Redberries") != count);
    }
}
