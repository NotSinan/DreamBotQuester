package org.dreambot.quests.paidquests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RetrieveVolenciaMossLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 3 && !Inventory.contains("Volencia moss");
    }

    @Override
    public int onLoop() {

        if (Inventory.contains("Grimy volencia moss")) {
            if (Inventory.interact("Grimy volencia moss", "Clean")) {
                Sleep.sleepUntil(() -> Inventory.contains("Volencia moss"), 3000);
            }
            return Timing.loopReturn();
        }

        return GameObjectHelper.goAndInteractWithGameObject(
                new Area(2844, 3038, 2853, 3029),
                "Rock",
                "Search",
                () -> Inventory.contains("Grimy volencia moss")
        );
    }
}
