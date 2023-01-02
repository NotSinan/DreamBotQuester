package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class RetrieveHammerLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return !Inventory.contains("Hammer");
    }

    @Override
    public int onLoop() {

        final Area VARROCK_GENERAL_STORE = new Area(3214, 3418, 3220, 3411);

        if (!QuestHelper.walkToArea(VARROCK_GENERAL_STORE)) {
            return Timing.loopReturn();
        }

        if (Shop.isOpen()) {
            if (Shop.purchase("Hammer", 1)) {
                Sleep.sleepUntil(() -> Inventory.contains("Hammer"), 3000);
            }
            return Timing.loopReturn();
        }

        NPC shopAssistant = NPCs.closest("Shop assistant");
        if (shopAssistant != null && shopAssistant.exists() && Interaction.delayEntityInteract(shopAssistant, "Trade")) {
            Sleep.sleepUntil(() -> Shop.isOpen(), () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
