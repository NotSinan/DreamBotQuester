package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class EnableRunDuringCombatLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return Players.getLocal().isInCombat() && Walking.getRunEnergy() >= 2 && !Walking.isRunEnabled();
    }

    @Override
    public int onLoop() {
        Timing.sleepForDelay();
        Walking.toggleRun();
        return Timing.loopReturn();
    }
}
