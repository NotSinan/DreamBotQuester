package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Fighting;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.GameObjectHelper;
import org.dreambot.utilities.helpers.WalkingHelper;
import org.dreambot.utilities.loadouts.setups.food.Food;

public class FightCountDraynor extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.VAMPIRE_SLAYER.getConfigID()) == 2;
  }

  @Override
  public int onLoop() {

    final Area DRAYNOR_STAIRS_TO_COUNT_AREA = new Area(3113, 3368, 3119, 3354, 0);
    final Area COUNT_DRAYNOR_AREA = new Area(3075, 9778, 3080, 9768);
    if (COUNT_DRAYNOR_AREA.contains(Players.getLocal())) {
      if (Fighting.shouldEat(3, Food.getBestFoodInInventory())) {
        if (!Fighting.haveFoodToEat()) {
          return GameObjectHelper.goAndInteractWithGameObject(
              COUNT_DRAYNOR_AREA,
              "Stairs",
              "Walk-Up",
              () -> !COUNT_DRAYNOR_AREA.contains(Players.getLocal()));
        }
        if (!Fighting.eatBestFood()) {
          return Timing.getSleepDelay();
        }
      }

      if (!Players.getLocal().isInCombat()) {
        GameObject coffin = GameObjects.closest("Coffin");

        if (coffin != null
            && Interaction.delayEntityInteract(coffin, "Open")
            && Sleep.sleepUntil(
                () -> Players.getLocal().isInCombat(),
                () -> Players.getLocal().isMoving(),
                8000,
                100)) {
          return Timing.loopReturn();
        }
        NPC countDraynor =
            NPCs.closest(
                vamp ->
                    vamp.getName().equals("Count Draynor")
                        && (vamp.getInteractingCharacter() == null
                            || vamp.getInteractingCharacter()
                                .equals(
                                    Players.getLocal()))); // don't get any vampyres attacking other
        // ppl
        if (countDraynor != null && Interaction.delayEntityInteract(countDraynor, "Attack")) {
          Sleep.sleepUntil(
              () -> Players.getLocal().isInCombat(),
              () -> Players.getLocal().isMoving(),
              3000,
              100);
        }
      }
      return Timing.loopReturn();
    }
    if (!WalkingHelper.walkToArea(DRAYNOR_STAIRS_TO_COUNT_AREA)) {
      return Timing.getSleepDelay();
    }

    GameObject stairs =
        GameObjects.closest(g -> g.hasAction("Walk-Down") && g.getName().equals("Stairs"));
    if (stairs != null && stairs.exists()) {
      if (Interaction.delayEntityInteract(stairs, "Walk-Down")) {
        Sleep.sleepUntil(
            () -> COUNT_DRAYNOR_AREA.contains(Players.getLocal()),
            () -> Players.getLocal().isMoving(),
            3000,
            100);
      }
    }
    return Timing.loopReturn();
  }
}
