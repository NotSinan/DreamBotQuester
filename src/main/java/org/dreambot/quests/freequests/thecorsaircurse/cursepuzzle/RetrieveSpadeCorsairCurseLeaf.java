package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class RetrieveSpadeCorsairCurseLeaf extends Leaf {
    private final Tile SPADE_TILE = new Tile(2552, 2846, 0);
    private final Tile TEST1 = new Tile(2578, 2838, 1);
    private final Tile TEST2 = new Tile(2578, 2839, 0);


    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        QuestHelper.addEntranceWebNodePair(TEST1, "Gangplank", "Cross", () -> Players.getLocal().getZ() == 1,
                TEST2, "Gangplank", "Cross", () -> Players.getLocal().getZ() == 0);
        return QuestHelper.pickupGroundSpawn(SPADE_TILE, "Spade");
    }

}
