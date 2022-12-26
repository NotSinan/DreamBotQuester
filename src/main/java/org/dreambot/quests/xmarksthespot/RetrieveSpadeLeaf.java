package org.dreambot.quests.xmarksthespot;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class RetrieveSpadeLeaf extends Leaf {

    private final Tile FALADOR_SPADE_AREA = new Tile(2981, 3370, 0);

    @Override
    public boolean isValid() {
        return !Inventory.contains("Spade") && PlayerSettings.getBitValue(QuestVarBits.QUEST_X_MARKS_THE_SPOT.getId()) == 0;
    }

    @Override
    public int onLoop() { return QuestHelper.pickupGroundSpawn(FALADOR_SPADE_AREA, "Spade"); }
}
