package org.dreambot.utilities.helpers.ironman;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.OwnedItems;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.BankHelper;
import org.dreambot.utilities.helpers.WalkingHelper;

public class IronmanRetrieval {
    public static int retrieveGarlic() {
        if (OwnedItems.contains("Garlic")) {
            return BankHelper.withdrawFromBank("Garlic", 1);
        }

        final Area GARLIC_AREA = new Area(3096, 3270, 3102, 3266, 1);

        if (!WalkingHelper.walkToArea(GARLIC_AREA)) {
            return Timing.getSleepDelay();
        }

        GameObject cupboard = GameObjects.closest("Cupboard");
        if (cupboard != null) {
            if (cupboard.hasAction("Open")) {
                if (Interaction.delayEntityInteract(cupboard, "Open")) {
                    Sleep.sleepUntil(() -> !cupboard.exists(), () -> Players.getLocal().isMoving(), 3000, 100);
                }
                return Timing.loopReturn();
            }
            if (Interaction.delayEntityInteract(cupboard, "Search")) {
                Sleep.sleepUntil(() -> Inventory.contains("Garlic"), () -> Players.getLocal().isMoving(), 3000, 100);
            }
        }
        return Timing.loopReturn();
    }

    public static int retrieveHammer() {
        if (OwnedItems.contains("Hammer")) {
            return BankHelper.withdrawFromBank("Hammer", 1);
        }
        final Area VARROCK_GENERAL_STORE = new Area(3214, 3418, 3220, 3411);

        if (!WalkingHelper.walkToArea(VARROCK_GENERAL_STORE)) {
            return Timing.getSleepDelay();
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
