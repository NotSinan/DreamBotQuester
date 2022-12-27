package org.dreambot.quests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveVolenciaMossLeaf extends Leaf {

    private final Area VOLENCIA_MOSS_AREA = new Area(2844, 3038, 2853, 3029);
    private final String GAME_OBJECT = "Rock";
    private final String ACTION = "Search";
    private final String ITEM = "Grimy volencia moss";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 3 && !Inventory.contains("Volencia moss");
    }

    @Override
    public int onLoop() {

        if (Inventory.contains(ITEM)) {
            if (Inventory.interact(ITEM, "Clean")) {
                Sleep.sleepUntil(() -> Inventory.contains("Volencia moss"), 3000);
            }
            return Timing.loopReturn();
        }

        return QuestHelper.goAndInteractWithGameObject(VOLENCIA_MOSS_AREA, GAME_OBJECT, ACTION, ITEM);
    }
}
