package org.dreambot.utilities;

import java.time.Instant;
import java.util.Arrays;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.utilities.helpers.WalkingHelper;
import org.dreambot.utilities.loadouts.setups.food.Food;

public class Fighting {
  public static int goAndKillNpc(Area area, String name) {
    if (!WalkingHelper.walkToArea(area)) {
      return Timing.getSleepDelay();
    }

    if (!Players.getLocal().isInCombat()) {
      NPC npc = NPCs.closest(n -> !n.isInCombat() && n.getName().equals(name) && area.contains(n));
      if (npc != null && npc.exists()) {
        if (npc.canReach()) {
          if (Interaction.delayEntityInteract(npc, "Attack")) {
            Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
          }
          return Timing.loopReturn();
        }
        if (!WalkingHelper.walkToTile(npc)) {
          return Timing.getSleepDelay();
        }
      }
    }
    return Timing.loopReturn();
  }

  private static int lastMaxHix = 0;
  public static int HPThreshold = 0;

  /**
   * Calculates a randomized threshold
   * @param maxHit
   * @return
   */
  public static boolean shouldEat(int maxHit, Food foodToEat) {
    int heals;
    if (foodToEat == null) {
      heals = 0;
    } else {
      heals = foodToEat.healAmount();
    }
    if (maxHit != lastMaxHix) {
      lastMaxHix = maxHit;
      HPThreshold = calculateEatThreshold(maxHit, heals);
    }

    if (HPThreshold == 0) {
      HPThreshold = calculateEatThreshold(maxHit, heals);
    }
    return Skills.getBoostedLevel(Skill.HITPOINTS) <= HPThreshold;
  }

  private static int calculateEatThreshold(int maxHit, int foodHeals) {
    int maxHP = Skills.getRealLevel(Skill.HITPOINTS);
    int LOWER_RANGE = 5;
    int UPPER_RANGE = maxHP - 2;
    int MAX_2 = maxHit * 2;
    // Calculate the center of the gaussian distribution
    int center = (int) Timing.clamp(MAX_2, LOWER_RANGE, UPPER_RANGE);

    // Generate a random number from a gaussian distribution centered around the center
    int threshold = (int) Calculations.nextGaussianRandom(center, 2);

    // If the player's max health minus the healing amount is less than 5, ignore this condition
    if (maxHP - foodHeals >= LOWER_RANGE) {
      // Attempt to maximize the healing amount of each food
      threshold = Math.min(threshold, UPPER_RANGE - foodHeals);
    }

    // Ensure that the threshold is within the acceptable range
    return (int) Timing.clamp(threshold, LOWER_RANGE, UPPER_RANGE);
  }

  public static boolean haveFoodToEat() {
    return Arrays.stream(Food.values()).anyMatch(food -> Inventory.contains(food.foodName()));
  }

  public static boolean eatBestFood() {
    Instant end = Instant.now().plusSeconds(3);
    if (haveFoodToEat()) {
      Logger.log("Taking 3s to try to eat some food");
      while (end.isAfter(Instant.now())
          && ScriptManager.getScriptManager().isRunning()
          && !ScriptManager.getScriptManager().isPaused()) {
        Item bestFood =
            Inventory.get(
                i ->
                    Arrays.stream(Food.values())
                        .anyMatch(food -> food.foodName().equals(i.getName())));
        if (bestFood == null || !bestFood.isValid()) {
          Logger.log("No more food found in inventory after finding some, but not ate any :-(");
          return false;
        }
        String action =
            (bestFood.hasAction("Drink") ? "Drink" : (bestFood.hasAction("Eat") ? "Eat" : null));
        if (action == null) {
          Logger.log(
              "No action Drink or Eat found on food item: "
                  + bestFood.getName()
                  + ", printing options...");
          for (String action2 : bestFood.getActions()) {
            if (action2 != null && !action2.isEmpty() && !action2.equalsIgnoreCase("null")) {
              Logger.log("[" + action2 + "]");
            }
          }
          return false;
        }
        if (Interaction.delayInventoryInteract(bestFood.getName(), action)) {
          Logger.log("Clicked \"" + action + "\" on some food: " + bestFood.getName());
          HPThreshold = 0;
          return true;
        }
      }
      Logger.log("3s timeout and didn't eat!");
      return false;
    }
    Logger.log("Did not find any food in inventory to eat!");
    return false;
  }
}
