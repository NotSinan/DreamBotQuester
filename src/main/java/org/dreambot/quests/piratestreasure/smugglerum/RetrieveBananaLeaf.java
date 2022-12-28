package org.dreambot.quests.piratestreasure.smugglerum;

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

public class RetrieveBananaLeaf extends Leaf {
    private final Area KARAMJA_BANANA_PLANTATION_AREA = new Area(
            new Tile(2927, 3154, 0),
            new Tile(2912, 3154, 0),
            new Tile(2911, 3156, 0),
            new Tile(2907, 3157, 0),
            new Tile(2907, 3163, 0),
            new Tile(2926, 3163, 0));
    @Override
    public boolean isValid() {
        return Inventory.count("Banana") < 10;
    }

    @Override
    public int onLoop() {
        final int count = Inventory.count("Banana");
        return QuestHelper.goAndInteractWithGameObject(KARAMJA_BANANA_PLANTATION_AREA, "Banana Tree", "Pick", () -> Inventory.count("Banana") > count);
    }
}
