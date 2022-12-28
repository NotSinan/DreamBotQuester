package org.dreambot.quests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveIronChainbodyLeaf extends Leaf {

    private final Area HORVIK_AREA = new Area(3227, 3441, 3232, 3433);
    private final String ITEM_NAME = "Iron chainbody";
    private final int QUANTITY = 1;
    private final String NPC_NAME = "Horvik";
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_BLACK_KNIGHTS_FORTRESS.getId()) == 0 &&
                Inventory.count("Coins") >= 300 &&
                !Inventory.contains("Iron chainbody");
    }

    @Override
    public int onLoop() {
        return QuestHelper.purchaseFromShop(HORVIK_AREA, ITEM_NAME, QUANTITY, NPC_NAME);
    }
}
