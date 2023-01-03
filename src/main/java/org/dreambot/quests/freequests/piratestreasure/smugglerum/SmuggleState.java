package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;

public class SmuggleState {
    /**
     * Variables that hold persistent values within the lifespan of the script
     */
    public static boolean talkedLuthasBeforeBananas = false;
    public static boolean talkedLuthasAfterBananas = false;
    public static boolean filledCrateWithBananas = false;
    public static boolean stashedRum = false;
    public static boolean crateSent = false;
    public static boolean atStart = true;

    public static boolean haveShippedRum() {
        return crateSent && stashedRum;
    }

    public static boolean isOnKaramja() {
        final Area karamjaZone1 = new Area(2688, 3235, 2903, 2879);
        final Area karamjaZone2 = new Area(2903, 2879, 2964, 3187);
        final Area karamjaBoat = new Area(2964, 3138, 2951, 3144, 1);
        return karamjaZone1.contains(Players.getLocal()) ||
                karamjaZone2.contains(Players.getLocal()) ||
                karamjaBoat.contains(Players.getLocal());
    }

    public static boolean hasRumOffKaramja() {
        return Inventory.contains("Karamjan rum") && !isOnKaramja();
    }
}
