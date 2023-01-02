package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveBronzeMedHelmLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_BLACK_KNIGHTS_FORTRESS.getId()) == 0 &&
                Inventory.count("Coins") >= 300 &&
                !Inventory.contains("Bronze med helm");
    }

    @Override
    public int onLoop() {
        final Area PEKSA_AREA = new Area(3073, 3431, 3077, 3427);
        final String ITEM_NAME = "Bronze med helm";
        final int QUANTITY = 1;
        final String NPC_NAME = "Peksa";
        return QuestHelper.purchaseFromShop(PEKSA_AREA, ITEM_NAME, QUANTITY, NPC_NAME);
    }
}
