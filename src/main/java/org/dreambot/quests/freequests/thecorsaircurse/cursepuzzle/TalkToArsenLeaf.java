package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToArsenLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return !CurseState.talkedToArsen()
        || (CurseState.talkedToIthoi()
            && CurseState.returnedToothpick()
            && CurseState.lookedThroughTelescope()
            && CurseState.finishedGnocci()
            && TalkToTessLeaf.leaveTessArea());
  }

  @Override
  public int onLoop() {
    return NPCHelper.goAndTalkToNpc(
        new Area(2553, 2859, 2559, 2853, 1),
        "Arsen the Thief",
        new String[] {"I hear you've been cursed."});
  }
}
