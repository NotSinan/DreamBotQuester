package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class RetrievePiratesMessageLeaf extends Leaf {
    private final Area CHEST_AREA = new Area(
            new Tile(3218, 3396, 1),
            new Tile(3222, 3396, 1),
            new Tile(3222, 3394, 1),
            new Tile(3218, 3392, 1));

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 2 && Inventory.contains("Chest key");
    }

    @Override
    public int onLoop() {
        if(Inventory.contains("Pirate message")) {
            Timing.sleepForDelay();
            Inventory.interact("Pirate message", "Read");
            return Timing.loopReturn();
        }

        if (QuestHelper.walkToArea(CHEST_AREA)) {
            GameObject chest = GameObjects.closest(g -> CHEST_AREA.contains(g) && g.getName().equals("Chest"));
            Item key = Inventory.get("Chest key");
            if(chest != null && chest.exists() && key != null && key.isValid()) {
                if(Interaction.delayUseItemOn(key, chest)) {
                    Sleep.sleepUntil(() -> Inventory.contains("Pirate message"), 8000, 100);
                }
            }
        }
        return Timing.loopReturn();
    }
}
