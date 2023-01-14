package org.dreambot.quests.freequests.doricsquest;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToDoricLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.DORICS_QUEST.getConfigID()) == 0
        || PlayerSettings.getConfig(FreeQuest.DORICS_QUEST.getConfigID()) == 10;
  }

  @Override
  public int onLoop() {
    final Area DORICS_QUEST_AREA = new Area(2950, 3454, 2953, 3449);
    final String[] DIALOGUES_OPTIONS = {
      "I wanted to use your anvils.",
      "Yes, I will get you the materials.",
      "Yes.",
      "Certainly, I'll be right back!"
    };
    return NPCHelper.goAndTalkToNpc(DORICS_QUEST_AREA, "Doric", DIALOGUES_OPTIONS);
  }
}
