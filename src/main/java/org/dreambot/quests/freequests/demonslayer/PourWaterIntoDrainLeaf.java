package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

/**
 * This class pours water in the drain that is outside of Varrock palace kitchen.
 */
public class PourWaterIntoDrainLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2 &&
                PlayerSettings.getBitValue(2568) == 0 && // Player setting to determine if a player has poured water in the drain.
                !Inventory.contains(2401) && // Silverlight key ID.
                Inventory.contains("Bucket of water");
    }

    @Override
    public int onLoop() {
        // Drain that is outside the kitchen at Varrock palace.
        final Area DRAIN_AREA = new Area(3225, 3497, 3227, 3495);
        if (!QuestHelper.walkToArea(DRAIN_AREA)) {
            return Timing.getSleepDelay();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
            }
        }

        GameObject drain = GameObjects.closest("Drain");
        Item bucketWater = Inventory.get("Bucket of water");
        if (drain != null && bucketWater != null && Interaction.delayUseItemOn(bucketWater, drain)) {
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
        }
        return Timing.loopReturn();
    }
}
