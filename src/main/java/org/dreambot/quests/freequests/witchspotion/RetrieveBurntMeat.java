package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.widget.helpers.ItemProcessing;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.WalkingHelper;

public class RetrieveBurntMeat extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) == 1 && !Inventory.contains("Burnt meat");
    }

    @Override
    public int onLoop() {
        final Area PORT_SARIM_FOOD_SHOP_AREA = new Area(
                new Tile(3016, 3203, 0),
                new Tile(3012, 3203, 0),
                new Tile(3012, 3210, 0),
                new Tile(3016, 3207, 0));
        final Area PORT_SARIM_RANGE_AREA = new Area(3015, 3240, 3019, 3236, 0);
        if (!Inventory.contains("Raw beef", "Cooked meat")) {
            if (!WalkingHelper.walkToArea(PORT_SARIM_FOOD_SHOP_AREA)) {
                return Timing.getSleepDelay();
            }
            if (!Shop.isOpen()) {
                NPC wydin = NPCs.closest(n -> n.hasAction("Trade") && n.getName().equals("Wydin") && PORT_SARIM_FOOD_SHOP_AREA.contains(n));
                if (wydin != null && wydin.exists() && Interaction.delayEntityInteract(wydin, "Trade")) {
                    Sleep.sleepUntil(Shop::isOpen, () -> Players.getLocal().isMoving(), 3000, 100);
                }
                return Timing.loopReturn();
            }
            Item rawBeef = Shop.get("Raw beef");
            if (rawBeef != null && rawBeef.isValid() && rawBeef.getAmount() > 0 && Interaction.delayItemInteract(rawBeef, "Buy 1")) {
                Sleep.sleepUntil(() -> Inventory.contains("Raw beef"), 3000);
            }
            return Timing.loopReturn();
        }

        if (!WalkingHelper.walkToArea(PORT_SARIM_RANGE_AREA)) {
            return Timing.getSleepDelay();
        }
        if (ItemProcessing.isOpen()) {
            if (ItemProcessing.makeAll("Cooked meat")) {
                Sleep.sleepUntil(() -> Inventory.contains("Burnt meat"), 3000);
            }
            return Timing.loopReturn();
        }
        GameObject range = GameObjects.closest("Range");
        if (range != null && range.distance() < 10 && Interaction.delayEntityInteract(range, "Cook")) {
            Sleep.sleepUntil(() -> Inventory.contains("Cooked meat", "Burnt meat"),
                    () -> Players.getLocal().isMoving() || Players.getLocal().isAnimating(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
