package org.dreambot.quests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToAuburyLeaf extends Leaf {

    private final Area AUBURY_AREA = new Area(3250, 3402, 3255, 3400);

    @Override
    public boolean isValid() {
        return AUBURY_AREA.contains(Players.getLocal()) &&
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 3 &&
                Inventory.contains("Research package") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 4 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 5 && !Inventory.contains("Research notes");
    }

    @Override
    public int onLoop() {

        NPC aubury = NPCs.closest("Aubury");
        if (!Dialogues.inDialogue() && aubury != null && aubury.interact("Talk-to")) {
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
                Dialogues.chooseFirstOption("I've been sent here with a package for you.");
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
        }
        return Timing.loopReturn();
    }
}
