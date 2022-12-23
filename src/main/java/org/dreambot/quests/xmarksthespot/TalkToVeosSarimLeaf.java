package org.dreambot.quests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class TalkToVeosSarimLeaf extends Leaf {

    private final Area VEOS_PORT_SARIM_AREA = new Area(3050, 3249, 3055, 3245);

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 6 && Inventory.contains("Ancient casket") ||
                PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 7;
    }

    @Override
    public int onLoop() {
        if (!VEOS_PORT_SARIM_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(VEOS_PORT_SARIM_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            NPC veos = NPCs.closest(npc -> npc.getName().equals("Veos"));
            if (veos != null && veos.interact("Talk-to")) {
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
        }

        return Timing.loopReturn();
    }
}
