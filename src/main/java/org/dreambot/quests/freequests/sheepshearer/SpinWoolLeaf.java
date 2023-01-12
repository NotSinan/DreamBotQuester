package org.dreambot.quests.freequests.sheepshearer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.WalkingHelper;

public class SpinWoolLeaf extends Leaf {

    @Override
    public boolean isValid() {
        //spin wool if have total wool count but not total spun wool count
        return PlayerSettings.getConfig(FreeQuest.SHEEP_SHEARER.getConfigID()) == 1 &&
                Inventory.count(i -> i.getName().equals("Wool") || i.getName().equals("Ball of wool")) >= 20 &&
                Inventory.count("Ball of wool") < 20;
    }

    @Override
    public int onLoop() {
        final Area SPINNING_WHEEL_AREA = new Area(3213, 3212, 3208, 3217, 1);
        if (!WalkingHelper.walkToArea(SPINNING_WHEEL_AREA)) {
            return Timing.getSleepDelay();
        }

        WidgetChild craftInterface = Widgets.getWidgetChild(270, 14, 38);
        if (craftInterface != null && craftInterface.isVisible()) {
            if (Interaction.delayWidgetInteract(craftInterface)) {
                Sleep.sleepUntil(() -> !Inventory.contains("Wool"), Players.getLocal()::isAnimating, 3000, 100);
            }
            return Timing.loopReturn();
        }

        GameObject spinningWheel = GameObjects.closest(obj -> obj != null && obj.getName().equals("Spinning wheel"));
        if (spinningWheel != null && spinningWheel.exists() && Interaction.delayEntityInteract(spinningWheel, "Spin")) {
            Sleep.sleepUntil(() -> {
                WidgetChild craftInterfaceTmp = Widgets.getWidgetChild(270, 14, 38);
                return craftInterfaceTmp != null && craftInterfaceTmp.isVisible();
            }, 3000);
        }
        return Timing.loopReturn();
    }
}
