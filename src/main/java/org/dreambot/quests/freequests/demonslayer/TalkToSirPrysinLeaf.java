package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToSirPrysinLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return (PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 1)
        || (PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2
            && Inventory.containsAll(2400, 2399, 2401));
  }

  @Override
  public int onLoop() {
    final Area SIR_PRYSIN_AREA = new Area(3201, 3475, 3206, 3469);
    final String[] DIALOGUE_OPTIONS = {
      "Aris said I should come and talk to you.",
      "I need to find Silverlight.",
      "He's back and unfortunately I've got to deal with him.",
      "So give me the keys!",
      "Can you give me your key?"
    };
    return NPCHelper.goAndTalkToNpc(SIR_PRYSIN_AREA, "Sir Prysin", DIALOGUE_OPTIONS);
  }
}
