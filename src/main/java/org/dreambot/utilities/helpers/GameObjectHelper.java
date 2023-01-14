package org.dreambot.utilities.helpers;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class GameObjectHelper {
  public static int goAndInteractWithGameObject(
      Area area, String gameObject, String action, Condition sleepUntilAfterInteract) {
    return goAndInteractWithGameObject(
        area, gameObject, action, sleepUntilAfterInteract, null, 0, 0);
  }

  public static int goAndInteractWithGameObject(
      Area area,
      String gameObject,
      String action,
      Condition sleepUntilAfterInteract,
      Condition sleepUntilReset,
      int timeout,
      int polling) {

    if (!WalkingHelper.walkToArea(area)) {
      return Timing.getSleepDelay();
    }

    GameObject interactableGameObject =
        GameObjects.closest(
            g -> g.getName().equals(gameObject) && area.contains(g) && g.hasAction(action));
    if (interactableGameObject != null) {
      if (interactableGameObject.canReach()) {
        if (Interaction.delayEntityInteract(interactableGameObject, action)) {
          if (sleepUntilReset == null) {
            Sleep.sleepUntil(
                sleepUntilAfterInteract, () -> Players.getLocal().isMoving(), 3000, 100);
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
}
