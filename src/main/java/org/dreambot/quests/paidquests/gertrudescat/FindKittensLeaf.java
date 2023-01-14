package org.dreambot.quests.paidquests.gertrudescat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.WalkingHelper;

public class FindKittensLeaf extends Leaf {

  List<NPC> cratesList;
  Map<NPC, Boolean> crates = new HashMap<>();

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 4
        && !Inventory.contains("Fluffs' kitten");
  }

  @Override
  public int onLoop() {
    if (!WalkingHelper.walkToArea(
        new Area( // kitten search area
            new Tile(3293, 3512, 0),
            new Tile(3298, 3517, 0),
            new Tile(3321, 3517, 0),
            new Tile(3325, 3505, 0),
            new Tile(3313, 3494, 0),
            new Tile(3299, 3494, 0)))) {
      return Timing.getSleepDelay();
    }

    if (cratesList == null) {
      cratesList = NPCs.all("Crate");
      for (NPC crate : cratesList) {
        crates.put(crate, false);
      }
    }

    if (Dialogues.inDialogue()) {
      if (Dialogues.canContinue() && Dialogues.continueDialogue()) {
        Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
      }
      return Timing.loopReturn();
    }

    if (!Inventory.contains("Fluffs' kitten")) {
      for (Map.Entry<NPC, Boolean> entry : crates.entrySet()) {
        Logger.log(crates);
        NPC crate = entry.getKey();
        boolean interacted = entry.getValue();
        if (!interacted) {
          if (Interaction.delayEntityInteract(crate, "Search")
              && Sleep.sleepUntil(
                  () -> crate.distance() <= 1, () -> Players.getLocal().isMoving(), 2400, 200)) {
            Logger.log(
                "Interacted 'Search' 'Crate', distance to us after sleeping: " + crate.distance());
            crates.put(crate, true);
          }
          return Timing.loopReturn();
        }
      }
    }
    return Timing.loopReturn();
  }
}
