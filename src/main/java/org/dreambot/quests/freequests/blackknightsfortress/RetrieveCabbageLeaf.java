package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveCabbageLeaf extends Leaf {

    private final Area CABBAGE_AREA = new Area(3049, 3506, 3055, 3502);
    private final String GAME_OBJECT = "Cabbage";
    private final String ACTION = "Pick";
    private final String ITEM = "Cabbage";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_BLACK_KNIGHTS_FORTRESS.getId()) == 0 && !Inventory.contains("Cabbage");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndInteractWithGameObject(CABBAGE_AREA, GAME_OBJECT, ACTION, () -> Inventory.contains(ITEM));
    }
}
