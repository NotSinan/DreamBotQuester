package org.dreambot.quests.freequests.romeoandjuliet;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToJulietLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.ROMEO_AND_JULIET.getConfigID()) == 10
        || PlayerSettings.getConfig(FreeQuest.ROMEO_AND_JULIET.getConfigID()) == 50
            && Inventory.contains("Cadava potion")
        || PlayerSettings.getConfig(FreeQuest.ROMEO_AND_JULIET.getConfigID()) == 50
            && PlayerSettings.getBitValue(12139) == 1;
  }

  @Override
  public int onLoop() {
    if (PlayerSettings.getBitValue(12139) == 0) {
      final Area JULIET_AREA = new Area(3155, 3426, 3161, 3425, 1);
      final String[] DIALOGUE_OPTIONS = {"Ok, thanks."};
      return NPCHelper.goAndTalkToNpc(JULIET_AREA, "Juliet", DIALOGUE_OPTIONS);
    }
    if (Dialogues.inDialogue()) {
      if (Dialogues.canContinue()) {
        Timing.sleepForDelay();
        Dialogues.continueDialogue();
      }
    }
    return Timing.loopReturn();
  }
}
