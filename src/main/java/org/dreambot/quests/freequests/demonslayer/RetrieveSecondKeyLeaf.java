package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class RetrieveSecondKeyLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 2 && PlayerSettings.getBitValue(2568) == 1;
    }

    @Override
    public int onLoop() {
        if (Inventory.contains("Bucket")) {
            Inventory.drop("Bucket");
            Sleep.sleepUntil(() -> !Inventory.contains("Bucket"), 3000);
            return Timing.loopReturn();
        }
        final Area RUSTY_KEY_AREA = new Area(3224, 9899, 3232, 9896);
        return QuestHelper.goAndInteractWithGameObject(RUSTY_KEY_AREA, "Rusty key", "Take", () -> Inventory.contains("Rusty key"));
    }
}
