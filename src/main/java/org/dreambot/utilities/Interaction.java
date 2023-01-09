package org.dreambot.utilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public class Interaction {
    public static boolean delayEntityInteract(Entity entity, String action) {
        Timing.sleepForDelay();
        return entity.interact(action);
    }

    public static boolean delayEntityInteract(Entity entity) {
        Timing.sleepForDelay();
        return entity.interact();
    }

    public static boolean delayItemInteract(Item item, String action) {
        Timing.sleepForDelay();
        return item.interact(action);
    }

    public static boolean delayInventoryInteract(String item, String action) {
        Timing.sleepForDelay();
        return Inventory.interact(item, action);
    }

    public static boolean delayWidgetInteract(WidgetChild widgetChild, String action) {
        Timing.sleepForDelay();
        return widgetChild.interact(action);
    }

    public static boolean delayWidgetInteract(WidgetChild widgetChild) {
        Timing.sleepForDelay();
        return widgetChild.interact();
    }

    public static boolean delayWalk(Tile tile) {
        Timing.sleepForDelay();
        return Walking.walk(tile);
    }

    public static boolean delayWalk(Entity entity) {
        Logger.log("Walking to entity: " + entity.getName() + " at tile: " + entity.getTile().toString());
        Timing.sleepForDelay();
        return Walking.walk(entity);
    }

    public static boolean delayUseItemOn(Item item, Entity entity) {
        Timing.sleepForDelay();
        return item.useOn(entity);
    }

    public static boolean delayUseItemOn(Item item1, Item item2) {
        Timing.sleepForDelay();
        return item1.useOn(item2);
    }
}
