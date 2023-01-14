package org.dreambot.quests.freequests.impcatcher;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class GiveBeadsLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.IMP_CATCHER.getConfigID()) == 0
        || PlayerSettings.getConfig(FreeQuest.IMP_CATCHER.getConfigID()) == 1;
  }

  @Override
  public int onLoop() {
    final Area MIZGOG_AREA = new Area(3099, 3166, 3107, 3159, 2);
    final String[] DIALOGUE_OPTIONS = {"Give me a quest please.", "Yes."};
    return NPCHelper.goAndTalkToNpc(MIZGOG_AREA, "Wizard Mizgog", DIALOGUE_OPTIONS);
  }
}
