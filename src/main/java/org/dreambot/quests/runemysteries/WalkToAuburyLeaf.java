package org.dreambot.quests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class WalkToAuburyLeaf extends Leaf {

    private final Area AUBURY_AREA = new Area(3250, 3402, 3255, 3400);


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 3 &&
                Inventory.contains("Research package") &&
                !AUBURY_AREA.contains(Players.getLocal());
    }

    @Override
    public int onLoop() {
        if (!AUBURY_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(AUBURY_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }
        return 400;
    }
}
