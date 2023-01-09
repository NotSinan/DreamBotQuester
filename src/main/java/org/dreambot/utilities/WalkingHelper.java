package org.dreambot.utilities;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.interactive.Entity;

import java.util.Arrays;
import java.util.Comparator;

public class WalkingHelper {

    /**
     * Walks to a random tile in an area.
     * @param area
     * @return true if inside area already, otherwise walks to random tile and returns false.
     */
    public static boolean walkToArea(Area area) {
        if(area.contains(Players.getLocal())) return true;
        Tile randTile = getWalkableTileInArea(area, 50);
        if (randTile == null) {
            Logger.log("Defined area returned null tile on API call Map.getWalkable(area.getRandomTile())");
            return false;
        }
        walkToTile(randTile);
        return false;
    }

    public static boolean walkToArea(Tile tile, int radius) {
        Area circle = tile.getArea(radius);
        return walkToArea(circle);
    }

    private static Tile getWalkableTileInArea(Area area, int tries) {

        Tile t = Map.getWalkable(area.getRandomTile());
        if (t == null) {
            return null;
        }

        if (tries <= 0) {
            return t;
        }

        if (!area.contains(t))  {
            t = getWalkableTileInArea(area, --tries);
        }

        return t;
    }
    public static boolean walkToTile(Tile tile) {
        if (tile.equals(Players.getLocal().getTile())) return true;
        if(Interaction.delayWalk(tile)) {
            Timing.loopReturn();
        }
        return false;
    }
    public static boolean walkToTile(int x, int y, int z) {
        Tile t = new Tile(x, y, z);
        if (t.equals(Players.getLocal().getTile())) return true;
        if(Interaction.delayWalk(t)) {
            Timing.loopReturn();
        }
        return false;
    }
    public static boolean walkToTile(int x, int y) {
        Tile t = new Tile(x, y);
        if (t.equals(Players.getLocal().getTile())) return true;
        if(Interaction.delayWalk(t)) {
            Timing.loopReturn();
        }
        return false;
    }
    public static boolean walkToTile(Entity entity) {
        if (entity.getTile().equals(Players.getLocal().getTile())) return true;
        if(Interaction.delayWalk(entity)) {
            Timing.loopReturn();
        }
        return false;
    }


    public static Area getClosestArea(Area... areas) {
        return Arrays.stream(areas)
                .min(
                        Comparator.comparingInt(area -> (int) (area.getCenter().distance()))
                ).get();
    }
}
