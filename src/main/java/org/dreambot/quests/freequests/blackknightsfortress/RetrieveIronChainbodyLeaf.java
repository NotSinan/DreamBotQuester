package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class RetrieveIronChainbodyLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_BLACK_KNIGHTS_FORTRESS.getId()) == 0 &&
                Inventory.count("Coins") >= 300 &&
                !Inventory.contains("Iron chainbody");
    }

    @Override
    public int onLoop() {
        final Area HORVIK_AREA = new Area(3227, 3441, 3232, 3433);
        return QuestHelper.purchaseFromShop(
                HORVIK_AREA,
                "Iron chainbody",
                1,
                "Horvik");
    }
}
