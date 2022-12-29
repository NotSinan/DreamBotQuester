package org.dreambot.quests.paidquests.druidicritual;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class EnchantMeatLeaf extends Leaf {

    private final Area CAULDRON_AREA = new Area(
            new Tile(2895, 9827, 0),
            new Tile(2890, 9827, 0),
            new Tile(2890, 9834, 0),
            new Tile(2896, 9834, 0)
    );

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_DRUIDIC_RITUAL.getId()) == 2 &&
                !Inventory.containsAll("Enchanted rat", "Enchanted beef", "Enchanted chicken", "Enchanted bear");
    }

    @Override
    public int onLoop() {

        if (!CAULDRON_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Interaction.delayWalk(CAULDRON_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (Inventory.contains("Raw rat meat", "Raw beef", "Raw chicken", "Raw bear meat")) {
            Item rawMeat = Inventory.get(item -> item.getName().startsWith("Raw") && item.hasAction("Use"));
            GameObject cauldronOfThunder = GameObjects.closest("Cauldron of Thunder");
            if(rawMeat != null && rawMeat.isValid() && cauldronOfThunder != null && cauldronOfThunder.exists() &&
                    Interaction.delayUseItemOn(rawMeat, cauldronOfThunder)) {
                int count = Inventory.count(item -> item.getName().startsWith("Raw"));
                Sleep.sleepUntil(() -> Inventory.count(item -> item.getName().startsWith("Raw")) != count, 3000);
            }
        }
        return Timing.loopReturn();
    }
}
