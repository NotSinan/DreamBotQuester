package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

/**
 * This class speaks to gives Wizard Traiborn the 25 bones to retrieve the 3rd key.
 */
public class TalkToWizardTraibornLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2
        && Inventory.containsAll(2401, 2400)
        && Inventory.count("Bones") == 25;
  }

  @Override
  public int onLoop() {
    final Area WIZARD_TRAIBORN_AREA = new Area(3099, 3168, 3117, 3154, 1);
    final String[] DIALOGUE_OPTIONS = {
      "Talk about Demon Slayer.",
      "I need to get a key given to you by Sir Prysin.",
      "Well, have you got any keys knocking around?",
      "I'll get the bones for you."
    };
    return NPCHelper.goAndTalkToNpc(WIZARD_TRAIBORN_AREA, "Wizard Traiborn", DIALOGUE_OPTIONS);
  }
}
