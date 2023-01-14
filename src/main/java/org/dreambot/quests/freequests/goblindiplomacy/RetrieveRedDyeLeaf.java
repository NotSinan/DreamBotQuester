package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class RetrieveRedDyeLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return Inventory.count("Redberries") >= 3;
  }

  @Override
  public int onLoop() {
    Area AGGIE_AREA = new Area(3083, 3261, 3088, 3256);
    final String[] DIALOGUE_OPTIONS = {
      "Can you make dyes for me please?",
      "What do you need to make red dye?",
      "Okay, make me some red dye please."
    };
    return NPCHelper.goAndTalkToNpc(AGGIE_AREA, "Aggie", DIALOGUE_OPTIONS);
  }
}
