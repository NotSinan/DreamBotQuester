package org.dreambot.utilities.helpers;

import java.time.Instant;
import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.methods.worldhopper.WorldHopper;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.utilities.Timing;

public class WorldsHelper {
  /**
   * Attempts to hop to another random similar world with a 60s timeout.
   * @return true after logged back in on another world, false if timeout occurs.
   */
  public static boolean hopSimilarWorld() {
    World initWorld = Worlds.getMyWorld();
    World randSimilarWorld =
        Worlds.getRandomWorld(
            world ->
                world.getWorld() != initWorld.getWorld()
                    && world.isPVP() == initWorld.isPVP()
                    && world.isNormal() == initWorld.isNormal()
                    && world.isMembers() == initWorld.isMembers()
                    && world.isLeagueWorld() == initWorld.isLeagueWorld()
                    && world.isTournamentWorld() == initWorld.isTournamentWorld()
                    && world.isFreshStart() == initWorld.isFreshStart());
    Instant end = Instant.now().plusSeconds(60);
    while (end.isAfter(Instant.now())) {
      GameState ourState = Client.getGameState();
      if (ourState != GameState.LOGGED_IN) {
        Sleep.sleepTicks(Timing.getTickDelay());
        continue;
      }

      if (Worlds.getCurrentWorld() != initWorld.getWorld()) {
        Logger.log(
            "Hopped to world: "
                + Worlds.getCurrentWorld()
                + " from: "
                + initWorld
                + "in "
                + ((end.toEpochMilli() - Instant.now().toEpochMilli()) / 1000)
                + "s");
        return true;
      }

      Timing.sleepForDelay();
      WorldHopper.hopWorld(randSimilarWorld);
      Sleep.sleepTicks(Timing.getTickDelay());
    }
    Logger.log("Worldhop timeout! Did not hop from world: " + initWorld + " in 60s...");
    return false;
  }
}
