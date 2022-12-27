package org.dreambot.quests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveSnakeWeedLeaf extends Leaf {

    private final Area SNAKE_WEED_AREA = new Area(2759, 3043, 2773, 3034);
    private final String GAME_OBJECT = "Marshy jungle vine";
    private final String ACTION = "Search";
    private final String ITEM = "Grimy snake weed";
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 1 && !Inventory.contains("Snake weed");
    }

    @Override
    public int onLoop() {

        if (Inventory.contains(ITEM)) {
            if (Inventory.interact(ITEM, "Clean")) {
                Sleep.sleepUntil(() -> Inventory.contains("Snake weed"), 3000);
            }
            return Timing.loopReturn();
        }

        return QuestHelper.goAndInteractWithGameObject(SNAKE_WEED_AREA, GAME_OBJECT, ACTION, ITEM);
    }
}
