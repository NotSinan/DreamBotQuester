package org.dreambot.quests.therestlessghost;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToFatherUrhneyLeaf extends Leaf {

    private final Area FATHER_URHNEY_AREA = new Area(3144, 3177, 3151, 3173);


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 1 && !Equipment.contains("Ghostspeak amulet");
    }

    @Override
    public int onLoop() {
        if (!FATHER_URHNEY_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(FATHER_URHNEY_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            NPC fatherAereck = NPCs.closest(npc -> npc.getName().equals("Father Urhney"));
            if (fatherAereck != null && fatherAereck.interact("Talk-to")) {
                Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
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
                Dialogues.chooseFirstOptionContaining("Father Aereck sent me to talk to you.", "He's got a ghost haunting his graveyard.");
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
        }

        return Timing.loopReturn();
    }
}
