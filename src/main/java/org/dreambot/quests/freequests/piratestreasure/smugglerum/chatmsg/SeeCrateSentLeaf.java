package org.dreambot.quests.freequests.piratestreasure.smugglerum.chatmsg;

import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.piratestreasure.smugglerum.SmuggleState;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeCrateSentLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return API.lastGameMessage.contains(
            "There is already some rum in Wydin's store, I should go and get that first.")
        || API.lastGameMessage.contains("Luthas hands you 30 coins.");
  }

  @Override
  public int onLoop() {
    SmuggleState.crateSent = true;
    API.lastGameMessage = "";
    return Timing.getSleepDelay();
  }
}
