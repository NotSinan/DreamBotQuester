package org.dreambot.quests.thecorsaircurse;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.walking.pathfinding.impl.web.WebFinder;
import org.dreambot.api.methods.walking.web.node.AbstractWebNode;
import org.dreambot.api.methods.walking.web.node.CustomWebPath;
import org.dreambot.api.methods.walking.web.node.impl.BasicWebNode;
import org.dreambot.api.methods.walking.web.node.impl.EntranceWebNode;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class WalkGangplankTest extends Leaf {
    private final Tile shipTestTile = new Tile(2912,3230,1);
    private final Tile GANGPLANK_2_TILE = new Tile(2909,3228,1);
    private final Tile GANGPLANK_1_TILE = new Tile(2909,3227,0);

    private final Tile[] TEST_PATH = {
            new Tile(2909, 3231, 1),
            new Tile(2912, 3229, 1)
    };
    private static boolean shouldAddCustomWebPath = false;


    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {

        if (!shouldAddCustomWebPath) {

            AbstractWebNode outsideNode = WebFinder.getWebFinder().getNearest(GANGPLANK_1_TILE, 7);
            AbstractWebNode insideNode = new BasicWebNode(2909, 3231, 1);
            EntranceWebNode outsideToInside = new EntranceWebNode(2909, 3227, 0);
            outsideToInside.setAction("Cross");
            outsideToInside.setEntityName("Gangplank");
            outsideToInside.setCondition(() -> Players.getLocal().getZ() == 0);


            CustomWebPath outsideToInsidePath = new CustomWebPath(false, outsideToInside, insideNode);
            outsideToInsidePath.connectToStart(WebFinder.getWebFinder().getId(outsideNode));
            WebFinder.getWebFinder().addCustomWebPath(outsideToInsidePath);

            EntranceWebNode insideToOutside = new EntranceWebNode(2909, 3228, 1);
            insideToOutside.setAction("Cross");
            insideToOutside.setEntityName("Gangplank");
            insideToOutside.setCondition(() -> Players.getLocal().getZ() == 1);

            CustomWebPath insideToOutsidePath = new CustomWebPath(false, insideToOutside, outsideNode);
            insideToOutsidePath.connectToStart(WebFinder.getWebFinder().getId(insideNode));
            WebFinder.getWebFinder().addCustomWebPath(insideToOutsidePath);

            shouldAddCustomWebPath = true;
            Logger.log("Added custom web path");
            return Timing.loopReturn();
        }
        QuestHelper.walkToTile(shipTestTile);
        return Timing.loopReturn();
    }
}
