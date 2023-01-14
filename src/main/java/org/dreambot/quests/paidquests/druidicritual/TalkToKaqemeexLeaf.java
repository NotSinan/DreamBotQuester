package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToKaqemeexLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(PaidQuest.DRUIDIC_RITUAL.getConfigID()) == 0
            && Inventory.contains("Raw rat meat", "Raw beef", "Raw chicken", "Raw bear meat")
        || PlayerSettings.getConfig(PaidQuest.DRUIDIC_RITUAL.getConfigID()) == 3;
  }

  @Override
  public int onLoop() {
    return NPCHelper.goAndTalkToNpc(
        new Area(2914, 3490, 2931, 3478), // kaqemeex area
        "Kaqemeex",
        new String[] {"I'm in search of a quest.", "Okay, I will try and help.", "Yes."});
  }
}
