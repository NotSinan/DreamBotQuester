package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class RetrieveBananaLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.count("Banana") < 10;
    }

    @Override
    public int onLoop() {
        final Area KARAMJA_BANANA_PLANTATION_AREA = new Area(
                new Tile(2927, 3154, 0),
                new Tile(2912, 3154, 0),
                new Tile(2911, 3156, 0),
                new Tile(2907, 3157, 0),
                new Tile(2907, 3163, 0),
                new Tile(2926, 3163, 0));
        final int count = Inventory.count("Banana");
        return QuestHelper.goAndInteractWithGameObject(KARAMJA_BANANA_PLANTATION_AREA, "Banana Tree", "Pick", () -> Inventory.count("Banana") > count);
    }
}
