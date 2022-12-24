package org.dreambot.quests.therestlessghost;

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

public class TalkToFatherAereckLeaf extends Leaf {

    private final Area FATHER_AERECK_AREA = new Area(3240, 3215, 3247, 3204);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 0;
    }

    @Override
    public int onLoop() {
        if (!FATHER_AERECK_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(FATHER_AERECK_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            NPC fatherAereck = NPCs.closest(npc -> npc.getName().equals("Father Aereck"));
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
                Dialogues.chooseFirstOptionContaining("I'm looking for a quest!", "Ok, let me help then.", "Yes.");
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
        }

        return Timing.loopReturn();
    }
}
