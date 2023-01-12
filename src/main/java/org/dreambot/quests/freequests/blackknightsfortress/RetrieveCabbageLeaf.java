package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RetrieveCabbageLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.BLACK_KNIGHTS_FORTRESS.getConfigID()) == 0 && !Inventory.contains("Cabbage");
    }

    @Override
    public int onLoop() {
        final Area CABBAGE_AREA = new Area(3049, 3506, 3055, 3502);
        final String GAME_OBJECT = "Cabbage";
        final String ACTION = "Pick";
        final String ITEM = "Cabbage";
        return GameObjectHelper.goAndInteractWithGameObject(
                CABBAGE_AREA,
                GAME_OBJECT,
                ACTION,
                () -> Inventory.contains(ITEM));
    }
}
