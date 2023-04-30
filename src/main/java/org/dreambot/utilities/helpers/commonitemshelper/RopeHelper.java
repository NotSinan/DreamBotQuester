package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.NPCHelper;

public class RopeHelper implements CommonItemsHelper {

  @Override
  public int getItem() {
    Area NED_AREA = new Area(3096, 3261, 3101, 3256);
    final String[] DIALOGUE_OPTIONS = {
      "Yes, I would like some rope.", "Okay, please sell me some rope."
    };

    return NPCHelper.goAndTalkToNpc(NED_AREA, "Ned", DIALOGUE_OPTIONS);
  }
}
