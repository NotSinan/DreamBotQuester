package org.dreambot.behaviour.combat.leaves;

import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

import java.util.List;

public class CombatLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        // Grab a nearby chicken
        NPC nearbyChicken = NPCs.closest(npc -> npc != null && npc.getName().equals("Chicken") && npc.canReach() && !npc.isInCombat());

        // If the chicken exists, let's perform a delayed entity interaction (in this case, an attack)
        // Notice that we call Timing.getSleepDelay() to randomly fetch a sleep delay for this interaction
        // This sleep is taken prior to taking the action
        if (nearbyChicken != null && Interaction.delayEntityInteract(nearbyChicken, "Attack", Timing.getSleepDelay())) {
            return Timing.loopReturn();
        }

        return Timing.loopReturn();
    }
}
