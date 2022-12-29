package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveEyeOfNewt extends Leaf {
    private final Area PORT_SARIM_MAGE_SHOP_AREA = new Area(3011, 3261, 3016, 3256, 0);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_WITCHS_POTION.getId()) == 1 && !Inventory.contains("Eye of newt");
    }

    @Override
    public int onLoop() {
        if (Inventory.count("Coins") < 3) {
            return QuestHelper.withdrawFromBank("Coins", 5);
        }
        return QuestHelper.purchaseFromShop(PORT_SARIM_MAGE_SHOP_AREA, "Eye of newt", 1, "Betty");
    }
}
