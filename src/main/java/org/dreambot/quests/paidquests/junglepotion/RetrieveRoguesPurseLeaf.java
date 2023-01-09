package org.dreambot.quests.paidquests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RetrieveRoguesPurseLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 9 && !Inventory.contains("Rogue's purse");
    }

    @Override
    public int onLoop() {
        final String ITEM = "Grimy rogue's purse";
        if (Inventory.contains(ITEM)) {
            if (Inventory.interact(ITEM, "Clean")) {
                Sleep.sleepUntil(() -> Inventory.contains("Rogue's purse"), 3000);
            }
            return Timing.loopReturn();
        }

        return GameObjectHelper.goAndInteractWithGameObject(
                new Area(2870, 3126, 2879, 3114), //adrigal area
                "Palm tree", "Search", () -> Inventory.contains(ITEM));
    }
}
