package org.dreambot.utilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.Comparator;

public class QuestHelper {

    public static int goAndInteractWithGameObject(Area area, String gameObject, String action, String item) {
        if (!area.contains(Players.getLocal())) {
            if (Walking.shouldWalk(6)) {
                Interaction.delayWalk(area.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GameObject interactableGameObject = GameObjects.closest(gameObject);
        if (interactableGameObject != null && Interaction.delayEntityInteract(interactableGameObject, action)) {
            Sleep.sleepUntil(() -> Inventory.contains(item), 2000);
        }
        return Timing.loopReturn();
    }


    public static int goAndKillNpc(Area area, String name) {
        if (!area.contains(Players.getLocal())) {
            if (Walking.shouldWalk(6)) {
                Interaction.delayWalk(area.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Players.getLocal().isInCombat()) {
            NPC npc = NPCs.closest(n -> !n.isInCombat() && n.getName().equals(name) && area.contains(n));
            if(npc != null && npc.exists()) {
                if(npc.canReach()) {
                    if(Interaction.delayEntityInteract(npc,"Attack")) {
                        Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
                    }
                    return Timing.loopReturn();
                }
                if(Walking.shouldWalk(6)) {
                    Interaction.delayWalk(npc);
                }
            }
        }
        return Timing.loopReturn();
    }


    public static int goAndTalkToNpc(Area area, String name, String[] dialogueOptions) {
        if (!area.contains(Players.getLocal())) {
            if (Walking.shouldWalk(6)) {
                Interaction.delayWalk(area.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            NPC npc = NPCs.closest(n -> n.getName().equals(name));
            if (npc != null) {
                if(npc.canReach()) {
                    if(Interaction.delayEntityInteract(npc, "Talk-to")) {
                        Sleep.sleepUntil(() -> Dialogues.inDialogue(), () -> Players.getLocal().isMoving(), 3000, 100);
                    }
                    return Timing.loopReturn();
                }
                if(Walking.shouldWalk()) {
                    Interaction.delayWalk(npc);
                }
                return Timing.loopReturn();
            }
            return Timing.loopReturn();
        }

        if (Dialogues.canContinue()) {
            Timing.sleepForDelay();
            if(Dialogues.continueDialogue()) {
                Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
            }
            return Timing.loopReturn();
        }

        if (Dialogues.areOptionsAvailable()) {
            if(dialogueOptions == null || dialogueOptions.length == 0) {
                Logger.log("See some options when no options passed to select in goAndTalkToNPC() function!");
                return Timing.loopReturn();
            }
            Timing.sleepForDelay();
            if(Dialogues.chooseFirstOptionContaining(dialogueOptions)) {
                Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
            }
            return Timing.loopReturn();
        }
        return Timing.loopReturn();
    }

    public static int withdrawFromBank(String itemName, int quantity) {
        if(!Bank.isOpen()) {
            Timing.sleepForDelay();
            Bank.open(BankLocation.getNearest());
            return Timing.loopReturn();
        }
        int count = Inventory.count(itemName) + Bank.count(itemName);
        if(count < quantity) {
            Logger.log("Attempted to withdraw itemName / quantity: " + itemName + " / " + quantity + " - but have only total quantity in bank + inventory: " + count + ", stopping script...!");
            return -1;
        }
        Timing.sleepForDelay();
        if(Bank.withdraw(itemName, quantity)) {
            Sleep.sleepUntil(() -> Inventory.contains(itemName) && Inventory.count(itemName) == quantity, 3000);
        }
        return Timing.loopReturn();
    }

    public static int purchaseFromShop(Area area, String itemName, int quantity, String npcName) {
        if (walkToArea(area)) {
            if (Shop.isOpen()) {
                Timing.sleepForDelay();
                if (Shop.purchase(itemName, quantity)) {
                    Sleep.sleepUntil(() -> Inventory.contains(itemName), 3000);
                }
                return Timing.loopReturn();
            }

            NPC shopAssistant = NPCs.closest(npcName);
            if (shopAssistant != null && Interaction.delayEntityInteract(shopAssistant, "Trade")) {
                Sleep.sleepUntil(() -> Shop.isOpen(), 3000);
            }
        }
        return Timing.loopReturn();
    }

    public static int pickupGroundSpawn(Tile tile, String name) {
        if(tile.distance() >= 15) {
            if(Walking.shouldWalk(6)) {
                Interaction.delayWalk(tile);
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
            Interaction.delayWalk(tile);
        }

        return Timing.loopReturn();
    }

    public static boolean inCutscene() {
        return PlayerSettings.getBitValue(542) == 1 && PlayerSettings.getBitValue(4606) == 1;
    }

    /**
     * Walks to a random tile in an area.
     * @param area
     * @return true if inside area already, otherwise walks to random tile and returns false.
     */
    public static boolean walkToArea(Area area) {
        if(area.contains(Players.getLocal())) return true;
        if(Walking.shouldWalk(6)) {
            Interaction.delayWalk(area.getRandomTile());
        }
        return false;
    }
}
