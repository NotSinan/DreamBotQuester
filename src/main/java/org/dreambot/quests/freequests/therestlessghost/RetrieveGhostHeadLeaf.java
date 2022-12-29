package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveGhostHeadLeaf extends Leaf {

    private final Area ALTAR_AREA = new Area(3112, 9569, 3121, 9564);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 3 && !Inventory.contains("Skull");
    }

    @Override
    public int onLoop() {
        if (QuestHelper.walkToArea(ALTAR_AREA)) {
            GameObject altar = GameObjects.closest("Altar");
            if (altar != null && Interaction.delayEntityInteract(altar, "Search")) {
                Sleep.sleepUntil(() -> Inventory.contains("Skull"), 3000);
            }
        }
        return Timing.loopReturn();
    }
}
