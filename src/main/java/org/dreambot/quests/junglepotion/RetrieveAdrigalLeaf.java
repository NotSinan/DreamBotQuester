package org.dreambot.quests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveAdrigalLeaf extends Leaf {

    private final Area ARDRIGAL_AREA = new Area(2870, 3126, 2879, 3114);
    private final String GAME_OBJECT = "Palm tree";
    private final String ACTION = "Search";
    private final String ITEM = "Grimy ardrigal";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 3 && !Inventory.contains("Ardrigal");
    }

    @Override
    public int onLoop() {

        if (Inventory.contains(ITEM)) {
            if (Inventory.interact(ITEM, "Clean")) {
                Sleep.sleepUntil(() -> Inventory.contains("Ardrigal"), 3000);
            }
            return Timing.loopReturn();
        }

        return QuestHelper.goAndInteractWithGameObject(ARDRIGAL_AREA, GAME_OBJECT, ACTION, ITEM);
    }
}
