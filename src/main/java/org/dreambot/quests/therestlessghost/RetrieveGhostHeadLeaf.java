package org.dreambot.quests.therestlessghost;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
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
        if (!ALTAR_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(ALTAR_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GameObject altar = GameObjects.closest("Altar");
        if (altar != null && altar.interact("Search")) {
            Sleep.sleepUntil(() -> Inventory.contains("Skull"), 3000);
            return Timing.loopReturn();
        }

        return Timing.loopReturn();
    }
}
