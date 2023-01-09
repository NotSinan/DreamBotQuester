package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.hint.HintArrow;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.WalkingHelper;

public class DigGardenLeaf extends Leaf {
    public static boolean escapingGardener() {
        final Area OUTSIDE_GARDEN_RUN_AREA = new Area(2992, 3366, 2997, 3372, 0);
        NPC gardener = NPCs.closest("Gardener");
        if (gardener != null && gardener.exists()) {
            Entity gardenerTarget = gardener.getInteractingCharacter();
            if (gardenerTarget != null && gardenerTarget.getName().equals(Players.getLocal().getName())) {
                WalkingHelper.walkToArea(OUTSIDE_GARDEN_RUN_AREA);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 3 && Inventory.contains("Spade");
    }

    @Override
    public int onLoop() {
        final Tile FALADOR_GARDEN_DIG_TILE = new Tile(2999, 3383, 0);
        if (HintArrow.exists()) {
            if (!DigGardenLeaf.escapingGardener()) {
                Sleep.sleepUntil(() -> !HintArrow.exists(), 15000);
            }
            return Timing.loopReturn();
        }

        if (!WalkingHelper.walkToTile(FALADOR_GARDEN_DIG_TILE)) {
            return Timing.getSleepDelay();
        }

        if (Inventory.interact("Spade", "Dig")) {
            Sleep.sleepUntil(() -> PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) != 3 || escapingGardener(), 8000);
        }
        return Timing.loopReturn();
    }
}
