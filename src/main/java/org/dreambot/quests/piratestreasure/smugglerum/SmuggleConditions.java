package org.dreambot.quests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public class SmuggleConditions {
    private final Area karamjaZone1 = new Area(2688, 3235, 2903, 2879);
    private final Area karamjaZone2 = new Area(2903, 2879, 2964, 3187);
    private final Area karamjaBoat = new Area(2964, 3138, 2951, 3144, 1);

    public boolean hasRumOffKaramja() {
        return karamjanRun() && offKaramja();
    }

    public boolean karamjanRun() {
        return Inventory.contains("Karamjan rum");
    }

    public boolean offKaramja() {
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
    public boolean crateSent() {

    }
}
