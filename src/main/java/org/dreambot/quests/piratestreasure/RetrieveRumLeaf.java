package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveRumLeaf extends Leaf {

    private final Area KARAMJA_PUB_AREA = new Area(2917, 3148, 2930, 3142);
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 1 &&
                Inventory.contains("Coins") &&
                !Inventory.contains("Karamjan rum");
    }

    @Override
    public int onLoop() {
        return QuestHelper.purchaseFromShop(KARAMJA_PUB_AREA, "Karamjan rum", 1, "Zambo");
    }
}
