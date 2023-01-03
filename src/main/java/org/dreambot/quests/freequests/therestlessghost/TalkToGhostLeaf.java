package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class TalkToGhostLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) == 2;
    }

    @Override
    public int onLoop() {

        if (Inventory.contains("Ghostspeak amulet")) {
            Timing.sleepForDelay();
            Inventory.interact("Ghostspeak amulet", "Wear");
            return Timing.loopReturn();
        }

        if (!QuestHelper.walkToArea(new Area(3247, 3195, 3252, 3190))) { //ghost area
            return Timing.getSleepDelay();
        }

        if (!Dialogues.inDialogue()) {
            NPC restlessGhost = NPCs.closest("Restless ghost");
            if (restlessGhost != null) {
                if (restlessGhost.interact("Talk-to")) {
                    Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
                }
                return Timing.loopReturn();
            }
            GameObject coffin = GameObjects.closest("Coffin");
            if (coffin != null && coffin.hasAction("Open") && Interaction.delayEntityInteract(coffin, "Open")) {
                Sleep.sleepUntil(() -> NPCs.closest("Restless ghost") != null, () -> Players.getLocal().isMoving(), 3000, 100);
            }
            return Timing.loopReturn();
        }

        if (Dialogues.canContinue()) {
            Timing.sleepForDelay();
            Dialogues.continueDialogue();
            Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
            return Timing.loopReturn();
        }

        if (Dialogues.areOptionsAvailable()) {
            Timing.sleepForDelay();
            Dialogues.chooseFirstOptionContaining("Yep, now tell me what the problem is.");
            Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
            return Timing.loopReturn();
        }

        return Timing.loopReturn();
    }
}
