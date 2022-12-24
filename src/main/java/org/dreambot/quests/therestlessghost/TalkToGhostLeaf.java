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

public class TalkToGhostLeaf extends Leaf {

    private final Area GHOST_AREA = new Area(3247, 3195, 3252, 3190);


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 2;
    }

    @Override
    public int onLoop() {

        if (Inventory.contains("Ghostspeak amulet")) {
            Inventory.interact("Ghostspeak amulet", "Wear");
            return Timing.loopReturn();
        }
        if (!GHOST_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(GHOST_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            GameObject coffin = GameObjects.closest("Coffin");
            if (coffin.hasAction("Open")) {
                coffin.interact("Open");
            } else {
                NPC restlessGhost = NPCs.closest(npc -> npc.getName().equals("Restless ghost"));
                if (restlessGhost != null && restlessGhost.interact("Talk-to")) {
                    Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
                }
            }
            return Timing.loopReturn();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }

            if (Dialogues.areOptionsAvailable()) {
                Dialogues.chooseFirstOptionContaining("Yep, now tell me what the problem is.");
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
        }

        return Timing.loopReturn();
    }
}
