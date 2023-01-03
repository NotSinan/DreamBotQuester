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
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.methods.worldhopper.WorldHopper;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.Entity;
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
        if (!walkToArea(area)) {
            return Timing.getSleepDelay();
        }

        GameObject interactableGameObject = GameObjects.closest(g -> g.getName().equals(gameObject) && area.contains(g) && g.hasAction(action));
        if (interactableGameObject != null && Interaction.delayEntityInteract(interactableGameObject, action)) {
            if(sleepUntilReset == null) {
                Sleep.sleepUntil(sleepUntilAfterInteract, () -> Players.getLocal().isMoving(), 3000, 100);
                return Timing.loopReturn();
            }
            Sleep.sleepUntil(sleepUntilAfterInteract, sleepUntilReset, timeout, polling);
        }
        return Timing.loopReturn();
    }

    public static int goAndInteractWithNPC(Area area, String npc, String action, Condition sleepUntilAfterInteract) {
        return goAndInteractWithNPC(area, npc, action, sleepUntilAfterInteract, null, 0, 0);
    }

    public static int goAndInteractWithNPC(Area area, String npc, String action, Condition sleepUntilAfterInteract, Condition sleepUntilReset, int timeout, int polling) {
        if (!walkToArea(area)) {
            return Timing.getSleepDelay();
        }

        NPC interactableNPC = NPCs.closest(g -> g.getName().equals(npc) && area.contains(g) && g.hasAction(action));
        if (interactableNPC != null && Interaction.delayEntityInteract(interactableNPC, action)) {
            if(sleepUntilReset == null) {
                Sleep.sleepUntil(sleepUntilAfterInteract, () -> Players.getLocal().isMoving(), 3000, 100);
                return Timing.loopReturn();
            }
            Sleep.sleepUntil(sleepUntilAfterInteract, sleepUntilReset, timeout, polling);
        }
        return Timing.loopReturn();
    }



    public static int goAndKillNpc(Area area, String name) {
        if (!walkToArea(area)) {
            return Timing.getSleepDelay();
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
                if (!walkToTile(npc)) {
                    return Timing.getSleepDelay();
                }
            }
        }
        return Timing.loopReturn();
    }


    public static int goAndTalkToNpc(Area area, String name, String[] dialogueOptions) {
        if (!walkToArea(area)) {
            return Timing.getSleepDelay();
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
                if(walkToTile(npc)) {
                    return Timing.getSleepDelay();
                }
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
            Bank.open();
            return Timing.loopReturn();
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
        if (!walkToArea(area)) {
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
            if (walkToTile(tile)) {
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
        if (walkToTile(tile)) {
            return Timing.getSleepDelay();
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
        Tile randTile = getWalkableTileInArea(area, 50);
        if (randTile == null) {
            Logger.log("Defined area returned null tile on API call Map.getWalkable(area.getRandomTile())");
            return false;
        }
        walkToTile(randTile);
        return false;
    }
    private static Tile getWalkableTileInArea(Area area, int tries) {

        Tile t = Map.getWalkable(area.getRandomTile());
        if (t == null) {
            return null;
        }

        if (tries <= 0) {
            return t;
        }

        if (!area.contains(t))  {
            t = getWalkableTileInArea(area, --tries);
        }

        return t;
    }
    public static boolean walkToTile(Tile tile) {
        if (tile.equals(Players.getLocal().getTile())) return true;
        if(Walking.shouldWalk(6) && Interaction.delayWalk(tile)) {
            Timing.loopReturn();
        }
        return false;
    }
    public static boolean walkToTile(int x, int y, int z) {
        Tile t = new Tile(x, y, z);
        if (t.equals(Players.getLocal().getTile())) return true;
        if(Walking.shouldWalk(6) && Interaction.delayWalk(t)) {
            Timing.loopReturn();
        }
        return false;
    }
    public static boolean walkToTile(int x, int y) {
        Tile t = new Tile(x, y);
        if (t.equals(Players.getLocal().getTile())) return true;
        if(Walking.shouldWalk(6) && Interaction.delayWalk(t)) {
            Timing.loopReturn();
        }
        return false;
    }
    public static boolean walkToTile(Entity entity) {
        if (entity.getTile().equals(Players.getLocal().getTile())) return true;
        if(Walking.shouldWalk(6) && Interaction.delayWalk(entity)) {
            Timing.loopReturn();
        }
        return false;
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
