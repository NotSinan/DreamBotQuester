package org.dreambot.utilities.helpers;

import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class NPCHelper {
    public static int goAndInteractWithNPC(Area area, String npc, String action, Condition sleepUntilAfterInteract) {
        return goAndInteractWithNPC(area, npc, action, sleepUntilAfterInteract, null, 0, 0);
    }

    public static int goAndInteractWithNPC(Area area, String npc, String action, Condition sleepUntilAfterInteract, Condition sleepUntilReset, int timeout, int polling) {
        Tile closestAreaTile = area.getNearestTile(Players.getLocal());
        if (closestAreaTile.distance() > 20 || closestAreaTile.getZ() != Players.getLocal().getZ()) {
            if (!WalkingHelper.walkToArea(area)) {
                return Timing.getSleepDelay();
            }
            return Timing.loopReturn();
        }

        NPC interactableNPC = NPCs.closest(g -> g.getName().equals(npc) && area.contains(g) && g.hasAction(action));
        if (interactableNPC != null) {
            if (interactableNPC.canReach()) {
                if (Interaction.delayEntityInteract(interactableNPC, action)) {
                    if(sleepUntilReset == null) {
                        Sleep.sleepUntil(sleepUntilAfterInteract, () -> Players.getLocal().isMoving(), 3000, 100);
                        return Timing.loopReturn();
                    }
                    Sleep.sleepUntil(sleepUntilAfterInteract, sleepUntilReset, timeout, polling);
                }
                return Timing.loopReturn();
            }
            if (!WalkingHelper.walkToTile(interactableNPC)) {
                return Timing.getSleepDelay();
            }
        }
        return Timing.loopReturn();
    }

    public static int goAndKillNpc(Area area, String name) {
        if (!WalkingHelper.walkToArea(area)) {
            return Timing.getSleepDelay();
        }

        if (!Players.getLocal().isInCombat()) {
            NPC npc = NPCs.closest(n -> !n.isInCombat() && n.getName().equals(name) && area.contains(n));
            if(npc != null && npc.exists()) {
                if (npc.canReach()) {
                    if(Interaction.delayEntityInteract(npc,"Attack")) {
                        Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
                    }
                    return Timing.loopReturn();
                }
                if (!WalkingHelper.walkToTile(npc)) {
                    return Timing.getSleepDelay();
                }
            }
        }
        return Timing.loopReturn();
    }

    public static int goAndTalkToNpc(Area area, String name, String[] dialogueOptions) {
        if (!WalkingHelper.walkToArea(area)) {
            return Timing.getSleepDelay();
        }

        if (!Dialogues.inDialogue()) {
            NPC npc = NPCs.closest(n -> n.getName().equals(name));
            if (npc != null) {
                if(npc.canReach()) {
                    if(Interaction.delayEntityInteract(npc, "Talk-to")) {
                        Sleep.sleepUntil(() -> Dialogues.inDialogue(), () -> Players.getLocal().isMoving(), 3000, 100);
                    }
                    return Timing.loopReturn();
                }
                if (!WalkingHelper.walkToTile(npc)) {
                    return Timing.getSleepDelay();
                }
            }
            return Timing.loopReturn();
        }

        if (Dialogues.canContinue()) {
            Timing.sleepForDelay();
            if(Dialogues.continueDialogue()) {
                Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
            }
            return Timing.loopReturn();
        }

        if (Dialogues.areOptionsAvailable()) {
            if(dialogueOptions == null || dialogueOptions.length == 0) {
                Logger.log("See some options when no options passed to select in goAndTalkToNPC() function!");
                return Timing.loopReturn();
            }
            Timing.sleepForDelay();
            if(Dialogues.chooseFirstOptionContaining(dialogueOptions)) {
                Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
            }
            return Timing.loopReturn();
        }
        return Timing.loopReturn();
    }
}
