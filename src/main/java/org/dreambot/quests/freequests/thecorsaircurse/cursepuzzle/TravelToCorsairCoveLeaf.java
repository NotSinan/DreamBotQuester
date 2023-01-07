package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class TravelToCorsairCoveLeaf extends Leaf {

    private static final Area CORSAIR_COVE_GROUND_LEVEL = new Area(
            new Tile[]{
                    new Tile(2471, 2876, 0),
                    new Tile(2498, 2879, 0),
                    new Tile(2511, 2874, 0),
                    new Tile(2526, 2876, 0),
                    new Tile(2529, 2878, 0),
                    new Tile(2535, 2877, 0),
                    new Tile(2543, 2871, 0),
                    new Tile(2550, 2871, 0),
                    new Tile(2570, 2876, 0),
                    new Tile(2604, 2869, 0),
                    new Tile(2618, 2834, 0),
                    new Tile(2475, 2826, 0)
            });
    private static final Area CHIEF_TESS_AREA = new Area(1998, 9012, 2026, 8993, 1);
    private static final Area CORSAIR_COVE_UPSTAIRS_AREAS = new Area(2603, 2873, 2506, 2814);

    private static final Area CAPTAIN_TOCK_PORT = new Area(2907, 3226, 2916, 3225, 0);
    private static final Tile CAPTAIN_TOCK_RIMMINGTON_TILE = new Tile(2910, 3226, 0);

    @Override
    public boolean isValid() {
        final Area CORSAIR_COVE_GROUND_LEVEL = new Area(
                new Tile[]{
                        new Tile(2471, 2876, 0),
                        new Tile(2498, 2879, 0),
                        new Tile(2511, 2874, 0),
                        new Tile(2526, 2876, 0),
                        new Tile(2529, 2878, 0),
                        new Tile(2535, 2877, 0),
                        new Tile(2543, 2871, 0),
                        new Tile(2550, 2871, 0),
                        new Tile(2570, 2876, 0),
                        new Tile(2604, 2869, 0),
                        new Tile(2618, 2834, 0),
                        new Tile(2475, 2826, 0)
                });
        final Area CHIEF_TESS_AREA = new Area(1998, 9012, 2026, 8993, 1);
        final Area CORSAIR_COVE_UPSTAIRS_AREAS = new Area(2603, 2873, 2506, 2814);

        return CHIEF_TESS_AREA.contains(Players.getLocal()) ||
                CORSAIR_COVE_GROUND_LEVEL.contains(Players.getLocal()) ||
                CORSAIR_COVE_UPSTAIRS_AREAS.contains(Players.getLocal());
    }

    @Override
    public int onLoop() {
        final Area CORSAIR_COVE_GROUND_LEVEL = new Area(
                new Tile[]{
                        new Tile(2471, 2876, 0),
                        new Tile(2498, 2879, 0),
                        new Tile(2511, 2874, 0),
                        new Tile(2526, 2876, 0),
                        new Tile(2529, 2878, 0),
                        new Tile(2535, 2877, 0),
                        new Tile(2543, 2871, 0),
                        new Tile(2550, 2871, 0),
                        new Tile(2570, 2876, 0),
                        new Tile(2604, 2869, 0),
                        new Tile(2618, 2834, 0),
                        new Tile(2475, 2826, 0)
                });
        final Area CAPTAIN_TOCK_PORT = new Area(2907, 3226, 2916, 3225, 0);
        final Tile CAPTAIN_TOCK_RIMMINGTON_TILE = new Tile(2910, 3226, 0);
        if (CAPTAIN_TOCK_RIMMINGTON_TILE.distance() > 145) {
            QuestHelper.walkToArea(CORSAIR_COVE_GROUND_LEVEL);
            return Timing.getSleepDelay();
        }
        return QuestHelper.goAndInteractWithNPC(CAPTAIN_TOCK_PORT, "Captain Tock", "Travel", () -> !CAPTAIN_TOCK_PORT.contains(Players.getLocal()));
    }


}
