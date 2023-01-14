package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;

import java.util.Arrays;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeBartenderMessageLeaf extends Leaf {

  @Override
  public boolean isValid() {

    return Arrays.stream(
            new String[] {
              "The barmaid signs your card.",
              "The bartender signs your card.",
              "Zambo signs your card.",
              "Blurberry signs your card.",
              "You think you see 2 bartenders signing 2 barcrawl cards.",
              "signing your barcrawl card",
              "The bartender scrawls his signature on your card.",
              "You can just about make out the bartender"
            })
        .anyMatch(phrase -> API.lastGameMessage.contains(phrase));
  }

  @Override
  public int onLoop() {
    AlfredGrimhandsBarcrawl.checkedCard = false;
    API.lastGameMessage = "";
    API.lastGameMessageTile = null;
    return Timing.loopReturn();
  }
}
