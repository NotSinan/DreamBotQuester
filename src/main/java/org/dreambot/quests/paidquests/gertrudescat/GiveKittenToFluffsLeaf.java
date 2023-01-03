package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.WalkingHelper;

public class GiveKittenToFluffsLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 4 && Inventory.contains("Fluffs' kitten");
    }

    @Override
    public int onLoop() {
        if (!WalkingHelper.walkToArea(new Area(3305, 3513, 3312, 3507, 1))) { //cat area
            return Timing.getSleepDelay();
        }

        NPC cat = NPCs.closest("Gertrude's cat");
        Item fluff = Inventory.get("Fluff's kitten");
        if (cat != null && cat.exists() && fluff != null && fluff.isValid() && Interaction.delayUseItemOn(fluff, cat)) {
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
