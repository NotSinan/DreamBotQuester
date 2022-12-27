package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveWhiteApronLeaf extends Leaf {
    private final Tile SARIM_WHITE_APRON_SPAWN = new Tile(3016, 3229, 0);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 0 &&
                !Inventory.contains("White apron") && !Equipment.contains("White apron");
    }

    @Override
    public int onLoop() { return QuestHelper.pickupGroundSpawn(SARIM_WHITE_APRON_SPAWN, "White apron"); }
}
