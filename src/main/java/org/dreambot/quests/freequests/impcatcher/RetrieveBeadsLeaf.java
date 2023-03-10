package org.dreambot.quests.freequests.impcatcher;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.BankHelper;
import org.dreambot.utilities.helpers.WalkingHelper;

public class RetrieveBeadsLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return !Inventory.containsAll("Black bead", "Red bead", "White bead", "Yellow bead");
  }

  @Override
  public int onLoop() {

    if (Inventory.count("Coins") < 500) {
      BankHelper.withdrawFromBank("Coins", 1000);
    }

    Area IMP_SPAWN_AREA = new Area(2816, 3188, 2840, 3166);
    if (!WalkingHelper.walkToArea(IMP_SPAWN_AREA)) {
      return Timing.getSleepDelay();
    }

    GroundItem bead =
        GroundItems.closest(
            item -> item.getName().endsWith("bead") && IMP_SPAWN_AREA.contains(item));
    if (bead != null) {
      if (Interaction.delayEntityInteract(bead, "Take")) {
        Logger.log("Bead on floor.");
        Sleep.sleep(2000, 3000);
      }
      return Timing.loopReturn();
    }

    if (Dialogues.inDialogue()) {
      if (Dialogues.canContinue()) {
        Dialogues.continueDialogue();
      }
      return Timing.loopReturn();
    }

    if (!Players.getLocal().isInCombat()) {
      NPC imp =
          NPCs.closest(
              i -> i.getName().equals("Imp") && IMP_SPAWN_AREA.contains(i) && i.isOnScreen());
      if (imp != null && imp.canReach() && Interaction.delayEntityInteract(imp, "Attack")) {
        Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
      }
      return Timing.loopReturn();
    }

    if (!Walking.isRunEnabled() && Walking.getRunEnergy() > 20) {
      if (Walking.toggleRun()) {
        Sleep.sleepUntil(() -> Walking.isRunEnabled(), 3000);
      }
    }

    return Timing.loopReturn();
  }
}
