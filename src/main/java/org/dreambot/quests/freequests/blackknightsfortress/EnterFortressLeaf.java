package org.dreambot.quests.freequests.blackknightsfortress;

import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class EnterFortressLeaf extends Leaf {


    @Override
    public boolean isValid() {

        final Area FORTRESS_ENTRANCE_AREA = new Area(3014, 3513, 3017, 3512);
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_BLACK_KNIGHTS_FORTRESS.getId()) == 1 &&
                FORTRESS_ENTRANCE_AREA.contains(Players.getLocal()) && Equipment.containsAll("Iron chainbody", "Bronze med helm");
    }

    @Override
    public int onLoop() {
        final Area INSIDE_FORTRESS_AREA = new Area(3015, 3516, 3017, 3515);

        if (!INSIDE_FORTRESS_AREA.contains(Players.getLocal())) {
            GameObject sturdyDoor = GameObjects.closest("Sturdy door");
            if (sturdyDoor != null && sturdyDoor.interact("Open")) {
                Sleep.sleepUntil(() -> INSIDE_FORTRESS_AREA.contains(Players.getLocal()), 3000);
            }
        }
        return Timing.loopReturn();
    }
}
