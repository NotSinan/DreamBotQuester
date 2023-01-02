package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class PourWaterIntoDrainLeaf extends Leaf {

    private final Area DRAIN_AREA = new Area(3225, 3497, 3227, 3495);

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 2 &&
                PlayerSettings.getBitValue(2568) == 0 &&
                !Inventory.contains(2401);
    }

    @Override
    public int onLoop() {
        if (!DRAIN_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(DRAIN_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
            }
        }

        GameObject drain = GameObjects.closest("Drain");
        if (drain != null && Inventory.interact("Bucket of water", "Use")) {
            drain.interact("Use");
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
        }
        return Timing.loopReturn();
    }
}
