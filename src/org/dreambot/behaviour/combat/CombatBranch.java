package org.dreambot.behaviour.combat;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.framework.Root;
import org.dreambot.utilities.Timing;

public class CombatBranch extends Root {

    @Override
    public boolean isValid() {
        return Timing.isValidTick() && !Players.localPlayer().isInCombat() && !Players.localPlayer().isMoving();
    }
}