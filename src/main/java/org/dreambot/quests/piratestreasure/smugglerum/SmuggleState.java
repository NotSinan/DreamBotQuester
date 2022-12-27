package org.dreambot.quests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public class SmuggleState {
    /**
     * Variables that hold persistent values within the lifespan of the script
     */
    public static boolean talkedLuthasBeforeBananas = false;
    public static boolean talkedLuthasAfterBananas = false;
    public static boolean filledCrateWithBananas = false;
    public static boolean stashedRum = false;
    public static boolean haveShippedRum = false;
    public static boolean employed = false;
    public static boolean lostRum = false;
    public static boolean atStart = false;
    public static boolean hadRumOffKaramja = false;



    public static void updateSmuggleStatus() {
        if ((hadRumOffKaramja() && !hasRumOffKaramja()) || lostRum()) {
            haveShippedRum = false;
            stashedRum = false;
            atStart = true;
            hadRumOffKaramja = false;
            lostRum = false;
        }

        if(crateSent()) {
            haveShippedRum();
            employed = false;
            filledCrateWithBananas = false;
            crateSent = false;
        }
    }

    private static void haveShippedRum() {
    }

    private static boolean lostRum() {
    }

    private static final Area karamjaZone1 = new Area(2688, 3235, 2903, 2879);
    private static final Area karamjaZone2 = new Area(2903, 2879, 2964, 3187);
    private static final Area karamjaBoat = new Area(2964, 3138, 2951, 3144, 1);

    private static boolean hadRumOffKaramja() {

    }
    public static boolean isOnKaramja() {
        return karamjaZone1.contains(Players.getLocal()) || karamjaZone2.contains(Players.getLocal()) || karamjaBoat.contains(Players.getLocal());
    }

    public static boolean hasRumOffKaramja() {
        return karamjanRum() && offKaramja();
    }

    public static boolean karamjanRum() {
        return Inventory.contains("Karamjan rum");
    }

    public static boolean offKaramja() {
        return !karamjaZone2.contains(Players.getLocal()) &&
                !karamjaZone1.contains(Players.getLocal()) &&
                !karamjaBoat.contains(Players.getLocal());
    }

    public boolean shippedRumFromWidget() {
        if(inPirateTreasureMenu()) {
            WidgetChild crateShipped = Widgets.getChildWidget(119, 15);
            if(crateShipped != null && crateShipped.isVisible() && crateShipped.getText().contains("the crate has been shipped")) {
                return true;
            }
        }
        return false;
    }

    public boolean inPirateTreasureMenu() {
        WidgetChild pirateTreasureMenu = Widgets.getChildWidget(119, 2);
        return pirateTreasureMenu != null && pirateTreasureMenu.isVisible();
    }

    public boolean shippedRumFromDialog() {
        return stashedRum() && crateSent();
    }

    private boolean stashedRum() {
        WidgetChild dialog = Widgets.getChildWidget(229, 1);
        return dialog != null && dialog.isVisible() && dialog.getText().contains("You stash the rum in the crate.");
    }
    public static boolean crateSent() {

    }

    public boolean
}
