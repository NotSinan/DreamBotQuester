package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class RetrieveGhostHeadLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) == 3 && !Inventory.contains("Skull");
    }

    @Override
    public int onLoop() {
        if (QuestHelper.walkToArea(new Area(3112, 9569, 3121, 9564))) { // altar area
            GameObject altar = GameObjects.closest("Altar");
            if (altar != null && Interaction.delayEntityInteract(altar, "Search")) {
                Sleep.sleepUntil(() -> Inventory.contains("Skull"), 3000);
            }
        }
        return Timing.loopReturn();
    }
}
