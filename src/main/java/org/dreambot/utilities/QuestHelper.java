package org.dreambot.utilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.Comparator;

public class QuestHelper {

    public static int goAndKillNpc(Area area, String name) {
        if (!area.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(area.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Players.getLocal().isInCombat()) {
            NPC npc = NPCs.closest(n -> !n.isInCombat() && n.getName().equals(name));
            npc.interact("Attack");
            Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
        }
        return Timing.loopReturn();
    }

    public static int goAndTalkToNpc(Area area, String name, String[] dialogueOptions) {
        if (!area.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(area.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            NPC npc = NPCs.closest(n -> n.getName().equals(name));
            if (npc != null && npc.interact("Talk-to")) {
                Sleep.sleepUntil(() -> Dialogues.inDialogue(), () -> Players.getLocal().isMoving(), 3000, 100);
            }
            return Timing.loopReturn();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }

            if (Dialogues.areOptionsAvailable()) {
                Dialogues.chooseFirstOptionContaining(dialogueOptions);
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
        }

        return Timing.loopReturn();
    }

    public static int withdrawFromBank(String itemName, int quantity) {
        if (Bank.open()) {
            Bank.withdraw(itemName, quantity);
            Sleep.sleepUntil(() -> Inventory.contains(itemName) && Inventory.count(itemName) == quantity, 3000);
        }
        return Timing.loopReturn();
    }

    public static int purchaseFromShop(Area area, String itemName, int quantity, String npcName) {
        if (!area.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(area.getRandomTile());
            }
        } else {
            if (Shop.isOpen()) {
                Shop.purchase(itemName, quantity);
                Sleep.sleepUntil(() -> Inventory.contains(itemName), 3000);
            } else {
                NPC shopAssistant = NPCs.closest(npcName);
                if (shopAssistant.interact("Trade")) {
                    Sleep.sleepUntil(() -> Shop.isOpen(), 3000);
                }
            }
        }
        return Timing.loopReturn();
    }

    public static int pickupGroundSpawn(Tile tile, String name) {
        if(tile.distance() >= 15) {
            if(Walking.shouldWalk(6)) {
                Walking.walk(tile);
            }
            return Timing.loopReturn();
        }

        Tile interactableTile = null;
        GroundItem groundItem = GroundItems.closest(x -> x != null && x.exists() && x.getTile().equals(tile) && x.getName().equals(name));
        if(groundItem != null) {
            if(groundItem.canReach()) interactableTile = groundItem.getTile();
            else {
                GameObject gob = GameObjects.getTopObjectOnTile(groundItem.getTile());
                if(gob != null && gob.canReach()){
                    Tile target = gob.getInteractableFrom().stream()
                            .filter(x -> x != null && x.distance(groundItem.getTile()) <= 1)
                            .min(Comparator.comparingDouble(Tile::distance))
                            .orElse(null);
                    if(target != null) {
                        interactableTile = target;
                    }
                }
            }
            if(interactableTile != null && interactableTile.canReach() && Interaction.delayEntityInteract(groundItem, "Take")) {
                int count = Inventory.count(name);
                Sleep.sleepUntil(() -> Inventory.count(name) > count, () -> Players.getLocal().isMoving(), 3000, 100);
                return Timing.loopReturn();
            }
        }

        if(Walking.shouldWalk(6)) {
            Walking.walk(tile);
        }

        return Timing.loopReturn();
    }

    /**
     * Walks to a random tile in an area.
     * @param area
     * @return true if inside area already, otherwise walks to random tile and returns false.
     */
    public static boolean walkToArea(Area area) {
        if(area.contains(Players.getLocal())) return true;
        if(Walking.shouldWalk(6)) {
            Walking.walk(area.getRandomTile());
        }
        return false;
    }
}
