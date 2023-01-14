package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToWiloughLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 1;
  }

  @Override
  public int onLoop() {
    return NPCHelper.goAndTalkToNpc(
        new Area(3213, 3437, 3223, 3429), // wilough area
        "Wilough",
        new String[] {"What will make you tell me?", "Okay then, I'll pay."});
  }
}
