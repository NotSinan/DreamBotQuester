package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.helpers.NPCHelper;

public class RetrieveBearMeatLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(PaidQuest.DRUIDIC_RITUAL.getConfigID()) == 0
        && !Inventory.contains("Raw bear meat");
  }

  @Override
  public int onLoop() {
    GroundItem rawRatMeat = GroundItems.closest("Raw bear meat");
    if (rawRatMeat != null && Interaction.delayEntityInteract(rawRatMeat, "Take")) {
      Sleep.sleepUntil(() -> Inventory.contains("Raw bear meat"), 3000);
    }
    return NPCHelper.goAndKillNpc(
        new Area(3070, 3612, 3094, 3597), // bear area
        "Grizzly bear");
  }
}
