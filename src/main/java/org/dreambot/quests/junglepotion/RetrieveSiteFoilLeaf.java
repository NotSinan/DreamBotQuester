package org.dreambot.quests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveSiteFoilLeaf extends Leaf {

    private final Area SITO_FOIL_AREA = new Area(2787, 3051, 2794, 3046);
    private final String GAME_OBJECT = "Scorched earth";
    private final String ACTION = "Search";
    private final String ITEM = "Grimy sito foil";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 5 && !Inventory.contains("Sito foil");
    }

    @Override
    public int onLoop() {

        if (Inventory.contains(ITEM)) {
            if (Inventory.interact(ITEM, "Clean")) {
                Sleep.sleepUntil(() -> Inventory.contains("Sito foil"), 3000);
            }
            return Timing.loopReturn();
        }

        return QuestHelper.goAndInteractWithGameObject(SITO_FOIL_AREA, GAME_OBJECT, ACTION, ITEM);
    }
}
