package org.dreambot.utilities.helpers;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;

public class CommonItemsHelper {

    public static int getWoadLeaf() {
        if (Inventory.count("Coins") < 40) {
            return BankHelper.withdrawFromBank("Coins", 40);
        }
        final Area WYSON_AREA = new Area(3024, 3383, 3029, 3375);
        final String[] DIALOGUE_OPTIONS = {"Yes please, I need woad leaves.", "How about 20 coins?"};
        return NPCHelper.goAndTalkToNpc(WYSON_AREA, "Wyson the gardener", DIALOGUE_OPTIONS);
    }

    public static int getBlueDye() {
        if (Inventory.count("Woad leaf") < 2) {
            return getWoadLeaf();
        }
        Area AGGIE_AREA = new Area(3083, 3261, 3088, 3256);
        final String[] DIALOGUE_OPTIONS = {
                "Can you make dyes for me please?",
                "What do you need to make blue dye?",
                "Okay, make me some blue dye please."
        };
        return NPCHelper.goAndTalkToNpc(AGGIE_AREA, "Aggie", DIALOGUE_OPTIONS);
    }

    public static int getOnion() {
        final Area ONION_AREA = new Area(3186, 3269, 3191, 3265);
        final int count = Inventory.count("Onion") + 1;
        return GameObjectHelper.goAndInteractWithGameObject(ONION_AREA, "Onion", "Pick", () -> Inventory.count("Onion") != count);
    }

    public static int getRedBerry() {
        final Area RED_BERRY_BUSH_AREA = new Area(3266, 3375, 3279, 3365);
        final int count = Inventory.count("Redberries");
        return GameObjectHelper.goAndInteractWithGameObject(RED_BERRY_BUSH_AREA, "Redberry bush", "Pick-from", () -> Inventory.count("Redberries") != count);
    }
}
