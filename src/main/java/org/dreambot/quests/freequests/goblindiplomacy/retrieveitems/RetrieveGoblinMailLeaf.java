package org.dreambot.quests.freequests.goblindiplomacy.retrieveitems;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.NPCHelper;

public class RetrieveGoblinMailLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 0 &&
                Inventory.count(item -> item.getName().endsWith("mail")) < 3;
    }

    @Override
    public int onLoop() {
        final Area GOBLIN_AREA = new Area(3243, 3241, 3261, 3227); // Lumbridge goblin area.
        GroundItem goblinMail = GroundItems.closest(item -> item.getName().equals("Goblin mail") && GOBLIN_AREA.contains(item));
        if (goblinMail != null) {
            if (Interaction.delayEntityInteract(goblinMail, "Take")) {
                Sleep.sleep(2000, 3000);
            }
            return Timing.loopReturn();
        }
        return NPCHelper.goAndKillNpc(GOBLIN_AREA, "Goblin");
    }
}
