package org.dreambot.quests.sheepshearer;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.framework.Leaf;

public class SpinWoolLeaf extends Leaf {

    Area SPINNING_WHEEL_AREA = new Area(3204, 3215, 3211, 3209, 1);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(179) == 1 && Inventory.count("Wool") == 20;
    }

    @Override
    public int onLoop() {
        if (!SPINNING_WHEEL_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(SPINNING_WHEEL_AREA.getRandomTile());
            }
        } else {
            GameObject spinningWheel = GameObjects.closest(obj -> obj != null && obj.getName().equals("Spinning wheel"));
            if (spinningWheel.interact("Spin")) {
                WidgetChild craftInterface = Widgets.getWidgetChild(270, 14, 38);
                Sleep.sleepUntil(() -> craftInterface.isVisible(), 3000);
                if (craftInterface != null) {
                    craftInterface.interact();
                }
            }
            Sleep.sleepWhile(() -> Inventory.contains("Wool"), Calculations.random(60000, 80000));
        }
        return Calculations.random(200, 500);
    }
}