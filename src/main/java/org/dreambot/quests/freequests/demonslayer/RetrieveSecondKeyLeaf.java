package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.GameObjectHelper;

/**
 * This class retrieves the Silverlight key from the Varrock sewers.
 */
public class RetrieveSecondKeyLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2 &&
                PlayerSettings.getBitValue(2568) == 1; // Player setting to determine if a player has poured water in the drain.
    }

    @Override
    public int onLoop() {
        if (Inventory.contains("Bucket")) {
            Inventory.drop("Bucket");
            Sleep.sleepUntil(() -> !Inventory.contains("Bucket"), 3000);
            return Timing.loopReturn();
        }
        final Area VARROCK_SEWER = new Area(3224, 9899, 3232, 9896); // This is where the key spawns after pouring water in the drain.
        return GameObjectHelper.goAndInteractWithGameObject(VARROCK_SEWER, "Rusty key", "Take", () -> Inventory.contains("Rusty key"));
    }
}