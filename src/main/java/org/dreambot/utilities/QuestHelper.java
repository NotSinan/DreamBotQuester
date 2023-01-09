package org.dreambot.utilities;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.methods.worldhopper.WorldHopper;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import java.time.Instant;
import java.util.Comparator;

public class QuestHelper {

    public static int goAndInteractWithGameObject(Area area, String gameObject, String action, Condition sleepUntilAfterInteract) {
        return goAndInteractWithGameObject(area, gameObject, action, sleepUntilAfterInteract, null, 0, 0);
    }

    public static int goAndInteractWithGameObject(Area area, String gameObject, String action, Condition sleepUntilAfterInteract, Condition sleepUntilReset, int timeout, int polling) {


        if (!WalkingHelper.walkToArea(area)) {
            return Timing.getSleepDelay();
        }


        GameObject interactableGameObject = GameObjects.closest(g -> g.getName().equals(gameObject) && area.contains(g) && g.hasAction(action));
        if (interactableGameObject != null) {
            if (interactableGameObject.canReach()) {
                if (Interaction.delayEntityInteract(interactableGameObject, action)) {
                    if (sleepUntilReset == null) {
                        Sleep.sleepUntil(sleepUntilAfterInteract, () -> Players.getLocal().isMoving(), 3000, 100);
                        return Timing.loopReturn();
                    }
                    Sleep.sleepUntil(sleepUntilAfterInteract, sleepUntilReset, timeout, polling);
                }
                return Timing.loopReturn();
            }
            if (!WalkingHelper.walkToTile(interactableGameObject)) {
                return Timing.getSleepDelay();
            }
        }
        return Timing.loopReturn();
    }


    public static int withdrawFromBank(String itemName, int quantity) {
        if(!Bank.isOpen()) {
            Timing.sleepForDelay();
            if (Bank.open()) {
                Timing.loopReturn();
            }
            return Timing.getSleepDelay();
        }

        if(Bank.count(itemName) <= 0) {
            Logger.log("Attempted to withdraw itemName / quantity: " + itemName + " / " + quantity + " - but have none in bank!");
            return Timing.loopReturn();
        }

        Timing.sleepForDelay();
        if(Bank.withdraw(itemName, quantity)) {
            Sleep.sleepUntil(() -> Inventory.count(itemName) >= quantity, 3000);
        }
        return Timing.loopReturn();
    }

    public static int purchaseFromShop(Area area, String itemName, int quantity, String npcName) {
        if (!WalkingHelper.walkToArea(area)) {
            return Timing.getSleepDelay();
        }
        if (Shop.isOpen()) {
            Item item = Shop.get(itemName);
            if(item != null && item.isValid() && item.getAmount() > 0) {
                Timing.sleepForDelay();
                if (Shop.purchase(itemName, quantity)) {
                    Sleep.sleepUntil(() -> Inventory.contains(itemName), 3000);
                }
            }
            return Timing.loopReturn();
        }

        NPC shopAssistant = NPCs.closest(npcName);
        if (shopAssistant != null && Interaction.delayEntityInteract(shopAssistant, "Trade")) {
            Sleep.sleepUntil(() -> Shop.isOpen(), () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }

    public static int pickupGroundSpawn(Tile tile, String name) {
        if(tile.distance() >= 15) {
            if (!WalkingHelper.walkToTile(tile)) {
                return Timing.getSleepDelay();
            }
        }

        Tile interactableTile = null;
        GroundItem groundItem = GroundItems.closest(x -> x.getTile().equals(tile) && x.getName().contains(name));
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
            if(interactableTile != null && Interaction.delayEntityInteract(groundItem, "Take")) {
                int count = Inventory.count(name);
                Sleep.sleepUntil(() -> Inventory.count(name) > count, () -> Players.getLocal().isMoving(), 3000, 100);
                return Timing.loopReturn();
            }
        }

        //check for fake GroundItem being actually a goddamn GameObject with <col=ff9040>Item Name</col> tag
        GameObject fakeGroundItem = GameObjects.closest(x -> x.getTile().equals(tile) && x.getName().contains(name));
        if(fakeGroundItem != null && fakeGroundItem.canReach()) {
            Tile target = fakeGroundItem.getInteractableFrom().stream()
                    .filter(x -> x != null && x.distance(fakeGroundItem.getTile()) <= 1)
                    .min(Comparator.comparingDouble(Tile::distance))
                    .orElse(null);
            if(target != null) {
                interactableTile = target;
            }

            if(interactableTile != null && Interaction.delayEntityInteract(fakeGroundItem, "Take")) {
                int count = Inventory.count(name);
                Sleep.sleepUntil(() -> Inventory.count(name) > count, () -> Players.getLocal().isMoving(), 3000, 100);
                return Timing.loopReturn();
            }
        }
        if (!WalkingHelper.walkToTile(tile)) {
            return Timing.getSleepDelay();
        }

        return Timing.loopReturn();
    }

    public static String getDialogue() {
        String txt = "";
        WidgetChild dialogWidget = Widgets.getWidgetChild(193, 2);
        if(dialogWidget == null || !dialogWidget.isVisible()) {
            dialogWidget = Widgets.getWidgetChild(231,6);
        }
        if(dialogWidget != null && dialogWidget.isVisible()) {
            txt = dialogWidget.getText();
            if(txt != null && !txt.isEmpty() && !txt.equalsIgnoreCase("null"))
            {
                Logger.log("NPC Dialogue: " + txt);
                return txt;
            }
        }
        txt = Dialogues.getNPCDialogue();
        if(txt != null && !txt.isEmpty() && !txt.equalsIgnoreCase("null")) {
            Logger.log("NPC Dialogue: " + txt);
            return txt;
        }
        return null;
    }

    /**
     * Attempts to hop to another random similar world with a 60s timeout.
     * @return true after logged back in on another world, false if timeout occurs.
     */
    public static boolean hopSimilarWorld() {
        World initWorld = Worlds.getMyWorld();
        World randSimilarWorld = Worlds.getRandomWorld(world -> world.getWorld() != initWorld.getWorld() &&
                world.isPVP() == initWorld.isPVP() &&
                world.isNormal() == initWorld.isNormal() &&
                world.isMembers() == initWorld.isMembers() &&
                world.isLeagueWorld() == initWorld.isLeagueWorld() &&
                world.isTournamentWorld() == initWorld.isTournamentWorld() &&
                world.isFreshStart() == initWorld.isFreshStart());
        Instant end = Instant.now().plusSeconds(60);
        while(end.isAfter(Instant.now())) {
            GameState ourState = Client.getGameState();
            if(ourState != GameState.LOGGED_IN) {
                Sleep.sleepTicks(Timing.getTickDelay());
                continue;
            }

            if(Worlds.getCurrentWorld() != initWorld.getWorld()) {
                Logger.log("Hopped to world: " + Worlds.getCurrentWorld() + " from: " + initWorld + "in " + ((end.toEpochMilli() - Instant.now().toEpochMilli()) / 1000) + "s");
                return true;
            }

            Timing.sleepForDelay();
            WorldHopper.hopWorld(randSimilarWorld);
            Sleep.sleepTicks(Timing.getTickDelay());
        }
        Logger.log("Worldhop timeout! Did not hop from world: " + initWorld + " in 60s...");
        return false;
    }
}
