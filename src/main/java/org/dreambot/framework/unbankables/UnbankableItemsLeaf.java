package org.dreambot.framework.unbankables;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class UnbankableItemsLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.contains("Antique lamp", "Lamp", "Lamp of knowledge", "Lamp of the gatherer", "Kourend favour certificate") ||
                API.lastGameMessage.contains("Your items cannot be stored in the bank.");
    }

    @Override
    public int onLoop() {
        Logger.log("Found unbankable items in inventory! Script is currently not able to handle them, so script is stopping to let you consume them");


        return -1;
    }

    //methods to handle them anyways since I had an antique lamp
    public static boolean haveRegularLamp() {
        return Inventory.contains("Antique lamp", "Lamp");
    }

    public static void rubRegularLamp() {
        Item lamp = Inventory.get("Antique lamp", "Lamp");
        if (Interaction.delayInventoryInteract(lamp.getName(), "Rub")) {
            Timing.loopReturn();
        }
    }
}
