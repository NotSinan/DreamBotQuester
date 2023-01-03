package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class GoToFortressLeaf extends Leaf {


    @Override
    public boolean isValid() {
        final Area FORTRESS_ENTRANCE_AREA = new Area(3014, 3513, 3017, 3512);
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_BLACK_KNIGHTS_FORTRESS.getId()) == 1 &&
                !FORTRESS_ENTRANCE_AREA.contains(Players.getLocal()) &&
                !Equipment.slotContains(EquipmentSlot.HAT, "Bronze med helm") ||
                !Equipment.slotContains(EquipmentSlot.CHEST, "Iron chainbody");
    }

    @Override
    public int onLoop() {
        final Area FORTRESS_ENTRANCE_AREA = new Area(3014, 3513, 3017, 3512);
        if (!FORTRESS_ENTRANCE_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(FORTRESS_ENTRANCE_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }
        return Timing.loopReturn();
    }
}
