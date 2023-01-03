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

public class GiveSeasonedSardineToCatLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 3 &&
                Inventory.contains("Seasoned sardine");
    }

    @Override
    public int onLoop() {
        if (!WalkingHelper.walkToArea(new Area(3305, 3513, 3312, 3507, 1))) { //cat area
            return Timing.getSleepDelay();
        }

        NPC cat = NPCs.closest("Gertrude's cat");
        Item sardine = Inventory.get("Seasoned sardine");
        if (cat != null && cat.exists() && sardine != null && sardine.isValid() && Interaction.delayUseItemOn(sardine, cat)) {
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
