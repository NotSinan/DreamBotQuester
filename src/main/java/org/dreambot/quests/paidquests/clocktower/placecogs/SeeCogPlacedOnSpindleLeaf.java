package org.dreambot.quests.paidquests.clocktower.placecogs;

import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeCogPlacedOnSpindleLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return API.lastGameMessage.contains("The cog fits perfectly.")
        || API.lastGameMessage.contains("You have already placed a cog here");
  }

  @Override
  public int onLoop() {
    final Area RED_COG_AREA = new Area(2564, 3245, 2573, 3239, 0);
    final Area BLUE_COG_AREA = new Area(2564, 3245, 2573, 3239, 1);
    final Area WHITE_COG_AREA = new Area(2564, 3245, 2573, 3239, 2);
    final Area BLACK_COG_AREA = new Area(2565, 9645, 2573, 9637, 0);

    if (BLUE_COG_AREA.contains(API.lastGameMessageTile)) {
      CogState.placedBlueCog = true;
      API.lastGameMessage = "";
      API.lastGameMessageTile = null;
      return Timing.getSleepDelay();
    }
    if (WHITE_COG_AREA.contains(API.lastGameMessageTile)) {
      CogState.placedWhiteCog = true;
      API.lastGameMessage = "";
      API.lastGameMessageTile = null;
      return Timing.getSleepDelay();
    }
    if (BLACK_COG_AREA.contains(API.lastGameMessageTile)) {
      CogState.placedBlackCog = true;
      API.lastGameMessage = "";
      API.lastGameMessageTile = null;
      return Timing.getSleepDelay();
    }
    if (RED_COG_AREA.contains(API.lastGameMessageTile)) {
      CogState.placedRedCog = true;
      API.lastGameMessage = "";
      API.lastGameMessageTile = null;
      return Timing.getSleepDelay();
    }

    return Timing.getSleepDelay();
  }
}
