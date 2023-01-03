package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class KillGoblinsLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2 &&
                Inventory.containsAll(2401, 2400) &&
                Inventory.count("Bones") < 25 &&
                !Inventory.containsAll(2399);
    }

    @Override
    public int onLoop() {
        final Area GOBLIN_AREA = new Area(3243, 3241, 3261, 3227);
        GroundItem bones = GroundItems.closest(item -> item.getName().equals("Bones") && GOBLIN_AREA.contains(item));
        if (bones != null) {
            if (Interaction.delayEntityInteract(bones, "Take")) {
                Sleep.sleep(2000, 3000);
            }
            return Timing.loopReturn();
        }
        return QuestHelper.goAndKillNpc(GOBLIN_AREA, "Goblin");
    }
}
