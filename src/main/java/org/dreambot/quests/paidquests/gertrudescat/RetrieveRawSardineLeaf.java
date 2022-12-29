package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveRawSardineLeaf extends Leaf {

    private final Area PORT_SARIM_FISH_SHOP_AREA = new Area(3011, 3229, 3017, 3223);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 0 && !Inventory.contains("Raw sardine");
    }

    @Override
    public int onLoop() {
        return QuestHelper.purchaseFromShop(PORT_SARIM_FISH_SHOP_AREA, "Raw sardine", 1, "Gerrant");
    }
}
