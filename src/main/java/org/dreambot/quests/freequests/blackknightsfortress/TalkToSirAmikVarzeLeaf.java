package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToSirAmikVarzeLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.BLACK_KNIGHTS_FORTRESS.getConfigID()) == 0
        && Inventory.containsAll("Iron chainbody", "Bronze med helm", "Cabbage");
  }

  @Override
  public int onLoop() {
    final Area SIR_AMIK_VARZE_AREA = new Area(2955, 3342, 2964, 3334, 2);
    final String[] DIALOGUE_OPTIONS = {
      "I seek a quest!", "I laugh in the face of danger!", "Ok, I'll do my best.", "Yes."
    };
    final String SIR_AMIK_VARZE = "Sir Amik Varze";
    return NPCHelper.goAndTalkToNpc(SIR_AMIK_VARZE_AREA, SIR_AMIK_VARZE, DIALOGUE_OPTIONS);
  }
}
