package org.dreambot.utilities.helpers;

import java.util.Comparator;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class GroundItemHelper {
  public static int pickupGroundSpawn(Tile tile, String name) {
    if (tile.distance() >= 15) {
      if (!WalkingHelper.walkToTile(tile)) {
        return Timing.getSleepDelay();
      }
    }

    Tile interactableTile = null;
    GroundItem groundItem =
        GroundItems.closest(x -> x.getTile().equals(tile) && x.getName().contains(name));
    if (groundItem != null) {
      if (groundItem.canReach()) interactableTile = groundItem.getTile();
      else {
        GameObject gob = GameObjects.getTopObjectOnTile(groundItem.getTile());
        if (gob != null && gob.canReach()) {
          Tile target =
              gob.getInteractableFrom().stream()
                  .filter(x -> x != null && x.distance(groundItem.getTile()) <= 1)
                  .min(Comparator.comparingDouble(Tile::distance))
                  .orElse(null);
          if (target != null) {
            interactableTile = target;
          }
        }
      }
      if (interactableTile != null && Interaction.delayEntityInteract(groundItem, "Take")) {
        int count = Inventory.count(name);
        Sleep.sleepUntil(
            () -> Inventory.count(name) > count, () -> Players.getLocal().isMoving(), 3000, 100);
        return Timing.loopReturn();
      }
    }

    // check for fake GroundItem being actually a goddamn GameObject with <col=ff9040>Item
    // Name</col> tag
    GameObject fakeGroundItem =
        GameObjects.closest(x -> x.getTile().equals(tile) && x.getName().contains(name));
    if (fakeGroundItem != null && fakeGroundItem.canReach()) {
      Tile target =
          fakeGroundItem.getInteractableFrom().stream()
              .filter(x -> x != null && x.distance(fakeGroundItem.getTile()) <= 1)
              .min(Comparator.comparingDouble(Tile::distance))
              .orElse(null);
      if (target != null) {
        interactableTile = target;
      }

      if (interactableTile != null && Interaction.delayEntityInteract(fakeGroundItem, "Take")) {
        int count = Inventory.count(name);
        Sleep.sleepUntil(
            () -> Inventory.count(name) > count, () -> Players.getLocal().isMoving(), 3000, 100);
        return Timing.loopReturn();
      }
    }
    if (!WalkingHelper.walkToTile(tile)) {
      return Timing.getSleepDelay();
    }

    return Timing.loopReturn();
  }
}
