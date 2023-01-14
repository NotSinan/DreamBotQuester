package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToWitchLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) == 0
        || PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) == 1
            && Inventory.containsAll("Onion", "Rat's tail", "Eye of newt", "Burnt meat");
  }

  @Override
  public int onLoop() {
    final Area WITCH_AREA = new Area(2965, 3208, 2970, 3203, 0);
    final String WITCH_NAME = "Hetty";
    final String[] DIALOGUE_OPTIONS = {
      "Yes, help me become one with my darker side.", "I am in search of a quest.", "Yes."
    };
    return NPCHelper.goAndTalkToNpc(WITCH_AREA, WITCH_NAME, DIALOGUE_OPTIONS);
  }
}
