package org.dreambot.quests.freequests.doricsquest;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

import java.util.Arrays;

public class GetOresLeaf extends Leaf {
    private final Tile BRONZE_PICKAXE_FALADOR_SPAWN = new Tile(3009, 3342, 0);
    private final Area RIMMINGTON_COPPER_ROCKS_AREA = new Area(2975, 3249, 2980, 3246, 0);
    private final Area RIMMINGTON_CLAY_ROCKS_AREA = new Area(2985, 3241, 2988, 3238, 0);
    private final Area RIMMINGTON_IRON_ROCKS_AREA = new Area(2972, 3236, 2967, 3243, 0);
    private final int[] COPPER_ROCK_IDS = {11161, 10943};
    private final int[] CLAY_ROCK_IDS = {11362, 11363};
    private final int[] IRON_ROCK_IDS = {11364, 11365};
    private int randomDropThreshold = (int) Timing.getRandomDelay(true, 1, 6, 2, 3);

    @Override
    public boolean isValid() {
        return Inventory.count("Iron ore") >= 2 &&
                Inventory.count("Clay") >= 6 &&
                Inventory.count("Copper ore") >= 4;
    }

    @Override
    public int onLoop() {
        if(!Inventory.contains("Bronze pickaxe", "Iron pickaxe") && !Equipment.contains("Bronze pickaxe", "Iron pickaxe")) {
            return QuestHelper.pickupGroundSpawn(BRONZE_PICKAXE_FALADOR_SPAWN, "Bronze pickaxe");
        }

        if(Inventory.count("Copper ore") < 4) {
            return mineRock(RIMMINGTON_COPPER_ROCKS_AREA, COPPER_ROCK_IDS);
        }

        if(Inventory.count("Clay") < 6) {
            return mineRock(RIMMINGTON_CLAY_ROCKS_AREA, CLAY_ROCK_IDS);
        }

        if(Inventory.count("Iron ore") < 2) {
            return mineRock(RIMMINGTON_IRON_ROCKS_AREA, IRON_ROCK_IDS);
        }

        return Timing.loopReturn();
    }
    private int mineRock(Area area, int[] rockIDs) {
        if(QuestHelper.walkToArea(area)) {
            GameObject copperRock = GameObjects.closest(g -> area.contains(g) && Arrays.stream(rockIDs).anyMatch(i -> i == g.getID()));
            if(copperRock != null && copperRock.exists() && Interaction.delayEntityInteract(copperRock, "Mine")) {
                int count = Inventory.getEmptySlots();
                Sleep.sleepUntil(() -> Inventory.getEmptySlots() != count, () -> Players.getLocal().isAnimating(), 3000, 100);
            }
            return Timing.loopReturn();
        }
        return Timing.loopReturn();
    }
}
