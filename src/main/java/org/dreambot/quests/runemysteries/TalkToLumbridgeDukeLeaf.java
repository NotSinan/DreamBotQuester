package org.dreambot.quests.runemysteries;

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
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToLumbridgeDukeLeaf extends Leaf {

    private Area LUMBRIDGE_DUKE_AREA = new Area(3208, 3225, 3213, 3218, 1);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 0 || PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 1 && !Inventory.contains("Air talisman");
    }

    @Override
    public int onLoop() {
        if (!LUMBRIDGE_DUKE_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(LUMBRIDGE_DUKE_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        NPC duke = NPCs.closest("Duke Horacio");
        if (!Dialogues.inDialogue() && duke != null && duke.interact("Talk-to")) {
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
            return Timing.loopReturn();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
            if (Dialogues.areOptionsAvailable()) {
                Dialogues.chooseFirstOption("Have you any quests for me?", "Sure, no problem", "Yes.");
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
        }

        return Timing.loopReturn();
    }
}
