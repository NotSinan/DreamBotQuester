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

public class TalkToArchmageLeaf extends Leaf {

    private final Area WIZARD_TOWER_AREA = new Area(3099, 9574, 3107, 9569);

    @Override
    public boolean isValid() {
        return WIZARD_TOWER_AREA.contains(Players.getLocal()) &&
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 1 &&
                Inventory.contains("Air talisman") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 2 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 3 && !Inventory.contains("Research package") ||
                WIZARD_TOWER_AREA.contains(Players.getLocal()) &&
                        PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 5 &&
                        Inventory.contains("Research notes");
    }

    @Override
    public int onLoop() {

        NPC duke = NPCs.closest("Archmage Sedridor");
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
                Dialogues.chooseFirstOption("I'm looking for the head wizard.", "Okay, here you are.", "Go ahead.", "Yes, certainly.");
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
        }
        return Timing.loopReturn();
    }
}
