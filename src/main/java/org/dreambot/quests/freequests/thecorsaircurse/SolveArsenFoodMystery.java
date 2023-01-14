package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class SolveArsenFoodMystery extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 30;
  }

  @Override
  public int onLoop() {
    return NPCHelper.goAndTalkToNpc(
        new Area(2553, 2859, 2559, 2853, 1),
        "Arsen the Thief",
        new String[] {"I hear Ithoi cooked the meal you ate that night."});
  }
}
