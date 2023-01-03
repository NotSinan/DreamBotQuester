package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class MixSardineAndDoogleLeavesLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 3 && Inventory.containsAll("Doogle leaves", "Raw sardine");
    }

    @Override
    public int onLoop() {
        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
            }
            return Timing.loopReturn();
        }
        Item doogle = Inventory.get("Doogle leaves");
        Item sardine = Inventory.get("Raw sardine");
        if (doogle != null && doogle.isValid() && sardine != null && sardine.isValid() && Interaction.delayUseItemOn(doogle, sardine)) {
            Sleep.sleepUntil(() -> Inventory.contains("Seasoned sardine"), 3000);
        }
        return Timing.loopReturn();
    }
}
