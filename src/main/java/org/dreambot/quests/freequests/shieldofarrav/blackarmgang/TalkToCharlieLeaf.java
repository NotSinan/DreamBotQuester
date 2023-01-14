package org.dreambot.quests.freequests.shieldofarrav.blackarmgang;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToCharlieLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.SHIELD_OF_ARRAV.getVarBitID()) == 0;
  }

  @Override
  public int onLoop() {

    Area CHARLIE_AREA = new Area(3207, 3394, 3211, 3390);
    final String[] DIALOGUE_OPTIONS = {
      "Is there anything down this alleyway?", "Do you think they would let me join?", "Yes"
    };
    final String CHARLIE = "Charlie the Tramp";
    return NPCHelper.goAndTalkToNpc(CHARLIE_AREA, CHARLIE, DIALOGUE_OPTIONS);
  }
}
