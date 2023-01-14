package org.dreambot.quests.paidquests.clocktower;

import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.walking.pathfinding.impl.local.LocalPathFinder;
import org.dreambot.api.methods.walking.pathfinding.impl.obstacle.impl.ClimbableObstacle;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class ClockTower extends Branch {
  public static boolean shouldAddStairs = true;

  @Override
  public boolean isValid() {
    if (shouldAddStairs) {
      LocalPathFinder.getLocalPathFinder()
          .addObstacle(new ClimbableObstacle("Staircase", "Climb-up", null, null, null));
      LocalPathFinder.getLocalPathFinder()
          .addObstacle(new ClimbableObstacle("Staircase", "Climb-down", null, null, null));
      shouldAddStairs = false;
    }

    return CheckRequirements.checkRequirements(PaidQuest.CLOCK_TOWER);
  }
}
