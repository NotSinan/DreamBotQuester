package org.dreambot.quests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveEggLeaf extends Leaf {
    private final Tile EGG_CHICKEN_WEST_SPAWN = new Tile(3185, 3297, 0);

    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 1) && !Inventory.contains("Egg");
    }


    @Override
    public int onLoop() { return QuestHelper.pickupGroundSpawn(EGG_CHICKEN_WEST_SPAWN,"Egg"); }

}
