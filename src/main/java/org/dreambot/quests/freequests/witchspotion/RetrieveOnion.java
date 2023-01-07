package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class RetrieveOnion extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) == 1 && !Inventory.contains("Onion");
    }

    @Override
    public int onLoop() {
        final Area ONION_AREA = new Area(2955, 3254, 2945, 3248, 0);
        if (!QuestHelper.walkToArea(ONION_AREA)) {
            return Timing.getSleepDelay();
        }

        GameObject onion = GameObjects.closest("Onion");
        if (onion != null && onion.exists() && Interaction.delayEntityInteract(onion, "Pick")) {
            Sleep.sleepUntil(() -> Inventory.contains("Onion"),
                    () -> Players.getLocal().isMoving() || Players.getLocal().isAnimating(),
                    3000, 100);
        }
        return Timing.loopReturn();
    }
}
