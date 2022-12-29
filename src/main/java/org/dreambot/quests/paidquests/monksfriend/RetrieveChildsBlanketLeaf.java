package org.dreambot.quests.monksfriend;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.web.node.impl.EntranceWebNode;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveChildsBlanketLeaf extends Leaf {

    private final Tile[] PATH_TO_CHILDS_BLANKET = {
            new Tile(2561, 9620, 0),
            new Tile(2563, 9620, 0),
            new Tile(2565, 9620, 0),
            new Tile(2565, 9618, 0),
            new Tile(2565, 9615, 0),
            new Tile(2565, 9613, 0),
            new Tile(2565, 9610, 0),
            new Tile(2565, 9607, 0)
    };

    private final Area LADDER_AREA = new Area(
            new Tile(2560, 3221, 0),
            new Tile(2562, 3221, 0),
            new Tile(2562, 3223, 0),
            new Tile(2560, 3223, 0)
    );

    private final Area CHILDS_BLANKET_AREA = new Area(2559, 9623, 2572, 9601);
    private boolean shouldAddCustomWebPath = false;


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_MONKS_FRIEND.getId()) == 10 && !Inventory.contains("Child's blanket");
    }

    @Override
    public int onLoop() {

        if (!shouldAddCustomWebPath) {
            EntranceWebNode ladder = new EntranceWebNode(2561, 3222, 0);
            ladder.setAction("Climb-down");
            ladder.execute();
//            WebFinder.getWebFinder().addWebNode(ladder);
//            WebFinder.getWebFinder().addCustomWebPath(new CustomWebPath(PATH_TO_CHILDS_BLANKET));
            shouldAddCustomWebPath = true;
            Logger.log("Here");
            return Timing.loopReturn();
        }

//        if (!CHILDS_BLANKET_AREA.contains(Players.getLocal())) {
//            QuestHelper.goAndInteractWithGameObject(LADDER_AREA, "Ladder", "Climb-down", ()-> Players.getLocal().getY() == 9000);
//        }

        return QuestHelper.pickupGroundSpawn(new Tile(2570, 9604, 0), "Child's blanket");
    }
}
