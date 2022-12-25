package org.dreambot.quests.impcatcher;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveBeadsLeaf extends Leaf {

    private final Area IMP_SPAWN_AREA = new Area(2623, 3218, 2637, 3200);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_IMP_CATCHER.getId()) == 0 &&
                !Inventory.contains("Black bead", "Red bead", "White bead", "Yellow bead");
    }

    @Override
    public int onLoop() {

        if (!IMP_SPAWN_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(IMP_SPAWN_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GroundItem beads = GroundItems.closest(item -> item.getName().endsWith("beads"));
        if (beads != null) {
            if(beads.interact("Take")) {
                Sleep.sleepUntil(() -> !beads.exists(), () -> Players.getLocal().isMoving(), 3000, 100);
            }
            return Timing.loopReturn();
        }

        NPC imp = NPCs.closest("Imp");
        if (imp != null && !Players.getLocal().isInCombat()) {
            if (imp.interact("Attack")) {
                Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
            }
            return Timing.loopReturn();
        }

        return Timing.loopReturn();
    }
}
