package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class DigGardenLeaf extends Leaf {
    private final Tile FALADOR_GARDEN_DIG_TILE = new Tile(2999, 3383, 0);
    private static final Area OUTSIDE_GARDEN_RUN_AREA = new Area(2992, 3366, 2997, 3372, 0);
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 3 && Inventory.contains("Spade");
    }

    @Override
    public int onLoop() {
        if (!QuestHelper.walkToTile(FALADOR_GARDEN_DIG_TILE)) {
            return Timing.loopReturn();
        }

        if(escapingGardener()) {
            return Timing.loopReturn();
        }

        if (Inventory.interact("Spade", "Dig")) {
            Sleep.sleepUntil(() -> PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) != 3 || escapingGardener(), 8000);
        }
        return Timing.loopReturn();
    }

    public static boolean escapingGardener() {
        NPC gardener = NPCs.closest("Gardener");
        if (gardener != null && gardener.exists()) {
            Entity gardenerTarget = gardener.getInteractingCharacter();
            if(gardenerTarget != null && gardenerTarget.getName().equals(Players.getLocal().getName())) {
                QuestHelper.walkToArea(OUTSIDE_GARDEN_RUN_AREA);
                return true;
            }
        }
        return false;
    }
}
