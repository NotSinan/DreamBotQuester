package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;

import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeAllFinishedMessage extends Leaf {

  @Override
  public boolean isValid() {
    return API.lastGameMessage.contains("You are too drunk to be able to read the barcrawl card.");
  }

  @Override
  public int onLoop() {
    AlfredGrimhandsBarcrawl.finishedCard = true;
    API.lastGameMessage = "";
    API.lastGameMessageTile = null;
    return Timing.loopReturn();
  }
}
