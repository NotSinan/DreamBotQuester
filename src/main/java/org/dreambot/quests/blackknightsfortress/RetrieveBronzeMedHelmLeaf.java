package org.dreambot.quests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveBronzeMedHelmLeaf extends Leaf {

    private final Area PEKSA_AREA = new Area(3073, 3431, 3077, 3427);
    private final String ITEM_NAME = "Bronze med helm";
    private final int QUANTITY = 1;
    private final String NPC_NAME = "Peksa";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_BLACK_KNIGHTS_FORTRESS.getId()) == 0 &&
                Inventory.count("Coins") >= 300 &&
                !Inventory.contains("Bronze med helm");
    }

    @Override
    public int onLoop() {
        return QuestHelper.purchaseFromShop(PEKSA_AREA, ITEM_NAME, QUANTITY, NPC_NAME);
    }
}
