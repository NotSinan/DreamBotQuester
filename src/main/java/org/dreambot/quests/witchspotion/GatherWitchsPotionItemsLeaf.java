package org.dreambot.quests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.helpers.ItemProcessing;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class GatherWitchsPotionItemsLeaf extends Leaf {
    private final Area PORT_SARIM_RAT_AREA = new Area(
            new Tile(3012, 3196, 0),
            new Tile(3001, 3196, 0),
            new Tile(3001, 3173, 0),
            new Tile(3021, 3172, 0),
            new Tile(3021, 3178, 0),
            new Tile(3010, 3179, 0),
            new Tile(3010, 3182, 0),
            new Tile(3020, 3182, 0),
            new Tile(3020, 3187, 0),
            new Tile(3013, 3188, 0));
    private final Area PORT_SARIM_FOOD_SHOP_AREA = new Area(
            new Tile(3016, 3203, 0),
            new Tile(3012, 3203, 0),
            new Tile(3012, 3210, 0),
            new Tile(3016, 3207, 0));
    private final Area PORT_SARIM_RANGE_AREA = new Area(3015, 3240, 3019, 3236, 0);
    private final Area PORT_SARIM_MAGE_SHOP_AREA = new Area(3011, 3261, 3016, 3256, 0);
    private final Area ONION_AREA = new Area(2955, 3254, 2945, 3248, 0);

    @Override
    public boolean isValid() {
        return !hasItems();
    }

    public static boolean hasItems() {
        return Inventory.containsAll("Onion","Rat's tail","Eye of newt","Burnt meat");
    }

    @Override
    public int onLoop() {
        if (!Inventory.contains("Rat's tail")) {
            if (!QuestHelper.walkToArea(PORT_SARIM_RAT_AREA)) {
                return Timing.loopReturn();
            }

            GroundItem ratTail = GroundItems.closest(g -> g.getName().equals("Rat's tail") && PORT_SARIM_RAT_AREA.contains(g));
            if (ratTail != null && ratTail.exists()) {
                if (Interaction.delayEntityInteract(ratTail, "Take")) {
                    Sleep.sleepUntil(() -> Inventory.contains("Rat's tail"), () -> Players.getLocal().isMoving(), 3000, 100);
                }
                return Timing.loopReturn();
            }

            return QuestHelper.goAndKillNpc(PORT_SARIM_RAT_AREA,"Rat");
        }
        if (!Inventory.contains("Burnt meat")) {
            if (!Inventory.contains("Raw beef", "Cooked meat")) {
                if (!QuestHelper.walkToArea(PORT_SARIM_FOOD_SHOP_AREA)) {
                    return Timing.loopReturn();
                }
                if (!Shop.isOpen()) {
                    NPC wydin = NPCs.closest(n -> n.hasAction("Trade") && n.getName().equals("Wydin") && PORT_SARIM_FOOD_SHOP_AREA.contains(n));
                    if(wydin != null && wydin.exists() && Interaction.delayEntityInteract(wydin, "Trade")) {
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

            if (!QuestHelper.walkToArea(PORT_SARIM_RANGE_AREA)) {
                return Timing.loopReturn();
            }
            if (ItemProcessing.isOpen()) {
                if(ItemProcessing.makeAll("Cooked meat")) {
                    Sleep.sleepUntil(() -> Inventory.contains("Burnt meat"), 3000);
                }
                return Timing.loopReturn();
            }
            GameObject range = GameObjects.closest("Range");
            if (range != null && range.distance() < 10 && Interaction.delayEntityInteract(range, "Cook")) {
                Sleep.sleepUntil(() -> Inventory.contains("Cooked meat", "Burnt meat"),
                        () -> Players.getLocal().isMoving() || Players.getLocal().isAnimating(), 3000,100);
            }
            return Timing.loopReturn();
        }

        if (!Inventory.contains("Eye of newt")) {
            if (Inventory.count("Coins") < 3) {
                return withdraw5Coins();
            }
            if (QuestHelper.walkToArea(PORT_SARIM_MAGE_SHOP_AREA)) {
                if (!Shop.isOpen()) {
                    NPC betty = NPCs.closest("Betty");
                    if (betty != null && betty.exists() && Interaction.delayEntityInteract(betty, "Trade")) {
                        Sleep.sleepUntil(Shop::isOpen, () -> Players.getLocal().isMoving(), 3000, 100);
                    }
                    return Timing.loopReturn();
                }
                Item eyeOfNewt = Shop.get("Eye of newt");
                if (eyeOfNewt != null && eyeOfNewt.isValid() && eyeOfNewt.getAmount() > 0 && Interaction.delayItemInteract(eyeOfNewt, "Buy 1")) {
                    Sleep.sleepUntil(() -> Inventory.contains("Eye of newt"), () -> Players.getLocal().isMoving(), 3000, 100);
                }
                return Timing.loopReturn();
            }
            return Timing.loopReturn();
        }

        if (QuestHelper.walkToArea(ONION_AREA)) {
            GameObject onion = GameObjects.closest("Onion");
            if (onion != null && onion.exists() && Interaction.delayEntityInteract(onion, "Pick")) {
                Sleep.sleepUntil(() -> Inventory.contains("Onion"),
                        () -> Players.getLocal().isMoving() || Players.getLocal().isAnimating(),
                        3000, 100);
            }
        }
        return Timing.loopReturn();
    }
    private int withdraw5Coins() {
        if(Bank.getLastBankHistoryCacheTime() <= 0) {
            Bank.open(BankLocation.getNearest());
            return Timing.loopReturn();
        }

        if(Bank.count("Coins") < 5) {
            Logger.log("Stopping script due to lack of coins! Total coins (bank + inventory): "+
                    (Bank.count("Coins") + Inventory.count("Coins")));
            return -1;
        }

        if(Bank.open(BankLocation.getNearest())) {
            Timing.sleepForDelay();
            if(Bank.withdraw("Coins",5)) {
                Sleep.sleepUntil(() -> Inventory.count("Coins") >= 5, 3000);
            }
        }
        return Timing.loopReturn();
    }
}
