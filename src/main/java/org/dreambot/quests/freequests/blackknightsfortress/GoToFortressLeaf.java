package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.WalkingHelper;

public class GoToFortressLeaf extends Leaf {


    @Override
    public boolean isValid() {
        final Area FORTRESS_ENTRANCE_AREA = new Area(3014, 3513, 3017, 3512);
        return PlayerSettings.getConfig(FreeQuest.BLACK_KNIGHTS_FORTRESS.getConfigID()) == 1 &&
                !FORTRESS_ENTRANCE_AREA.contains(Players.getLocal()) &&
                !Equipment.slotContains(EquipmentSlot.HAT, "Bronze med helm") ||
                !Equipment.slotContains(EquipmentSlot.CHEST, "Iron chainbody");
    }

    @Override
    public int onLoop() {
        final Area FORTRESS_ENTRANCE_AREA = new Area(3014, 3513, 3017, 3512);
        WalkingHelper.walkToArea(FORTRESS_ENTRANCE_AREA);
        return Timing.getSleepDelay();
    }
}
