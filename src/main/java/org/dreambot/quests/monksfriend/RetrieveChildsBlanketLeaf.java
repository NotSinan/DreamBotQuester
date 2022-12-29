package org.dreambot.quests.monksfriend;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.walking.pathfinding.impl.web.WebFinder;
import org.dreambot.api.methods.walking.web.node.CustomWebPath;
import org.dreambot.api.methods.walking.web.node.impl.BasicWebNode;
import org.dreambot.api.methods.walking.web.node.impl.EntranceWebNode;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveChildsBlanketLeaf extends Leaf {

    private final Area AREA = new Area(2563, 9620, 2561, 9620, 0);


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_MONKS_FRIEND.getId()) == 10 && !Inventory.contains("Child's blanket");
    }

    @Override
    public int onLoop() {
        return Timing.loopReturn();
    }
}
