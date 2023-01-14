package org.dreambot.quests.freequests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToVeosLumbridgeLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return Inventory.contains("Spade")
            && PlayerSettings.getBitValue(FreeQuest.X_MARKS_THE_SPOT.getVarBitID()) == 0
        || PlayerSettings.getBitValue(FreeQuest.X_MARKS_THE_SPOT.getVarBitID()) == 1
        || PlayerSettings.getBitValue(FreeQuest.X_MARKS_THE_SPOT.getVarBitID()) == 2
            && !Inventory.contains("Treasure scroll");
  }

  @Override
  public int onLoop() {
    final Area VEOS_AREA = new Area(3226, 3242, 3233, 3239);
    final String VEOS_NAME = "Veos";
    final String[] DIALOGUE_OPTIONS = {
      "I'm looking for a quest.",
      "Sounds good, what should I do?",
      "Okay, thanks Veos.",
      "Yes.",
      "Can I help?"
    };
    return NPCHelper.goAndTalkToNpc(VEOS_AREA, VEOS_NAME, DIALOGUE_OPTIONS);
  }
}
