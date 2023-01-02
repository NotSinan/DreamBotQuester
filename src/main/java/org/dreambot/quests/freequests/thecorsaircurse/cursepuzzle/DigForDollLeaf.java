package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class DigForDollLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return CurseState.talkedToIthoi() &&
                        CurseState.returnedToothpick() &&
                        CurseState.talkedToColin() &&
                        Inventory.contains("Spade");
    }
    @Override
    public int onLoop() {
        if (!QuestHelper.walkToArea(new Area(2501, 2843, 2510, 2836, 0))) { //doll dig area
            return Timing.loopReturn();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                return Timing.loopReturn();
            }

            if (Dialogues.areOptionsAvailable()) {
                if (Dialogues.chooseFirstOptionContaining("Search for the possessed doll and face the consequences.")) {
                    Sleep.sleepUntil(() -> {
                        String dialog2 = QuestHelper.getDialogue();
                        return dialog2 != null && dialog2.contains("That sounds more like clockwork");
                    }, 8000);
                }
            }
            return Timing.loopReturn();
        }

        if (Interaction.delayInventoryInteract("Spade", "Dig")) {
            Sleep.sleepUntil(Dialogues::inDialogue, () -> Players.getLocal().isMoving(), 3000, 100);
        }

        return Timing.loopReturn();
    }
}
