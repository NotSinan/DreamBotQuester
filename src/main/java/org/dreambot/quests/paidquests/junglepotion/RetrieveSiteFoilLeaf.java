package org.dreambot.quests.paidquests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RetrieveSiteFoilLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 5 && !Inventory.contains("Sito foil");
    }

    @Override
    public int onLoop() {
        final String ITEM = "Grimy sito foil";
        if (Inventory.contains(ITEM)) {
            if (Inventory.interact(ITEM, "Clean")) {
                Sleep.sleepUntil(() -> Inventory.contains("Sito foil"), 3000);
            }
            return Timing.loopReturn();
        }

        return GameObjectHelper.goAndInteractWithGameObject(
                new Area(2787, 3051, 2794, 3046),
                "Scorched earth",
                "Search",
                () -> Inventory.contains(ITEM)
        );
    }
}
