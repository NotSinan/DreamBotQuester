package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.Client;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.WalkingHelper;

public class BurnIthoiLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 45;
  }

  @Override
  public int onLoop() {
    if (!WalkingHelper.walkToArea(
        new Area(2527, 2840, 2534, 2833, 0))) { // driftwood area under ithoi's house
      return Timing.getSleepDelay();
    }

    GameObject driftwood = GameObjects.closest("Driftwood");
    if (driftwood != null && driftwood.exists()) {
      Item tinderbox = Inventory.get("Tinderbox");
      if (tinderbox.useOn(driftwood)) {
        Sleep.sleepUntil(
            () -> Client.isDynamicRegion() || Client.isInCutscene(),
            () -> Players.getLocal().isMoving(),
            8000,
            100);
      }
    }
    return Timing.loopReturn();
  }
}
