package org.dreambot.quests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class MixSardineAndDoogleLeavesLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 3 && Inventory.containsAll("Doogle leaves", "Raw sardine");
    }

    @Override
    public int onLoop() {
        if (Inventory.containsAll("Doogle leaves", "Raw sardine")) {
            Inventory.interact("Doogle leaves", "Use");
            Sleep.sleepUntil(() -> Inventory.isItemSelected(), 3000);
            Inventory.interact("Raw sardine", "Use");
            if (Dialogues.inDialogue()) {
                if (Dialogues.canContinue()) {
                    Dialogues.continueDialogue();
                    Sleep.sleepUntil(() -> !Dialogues.inDialogue(), 3000);
                }
            }
        }
        return Timing.loopReturn();
    }
}
