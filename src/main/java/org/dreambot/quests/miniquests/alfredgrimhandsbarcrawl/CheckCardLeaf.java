package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class CheckCardLeaf extends Leaf {
    private final Area BARBARIANS_OUTSIDE_AGILITY_AREA = new Area(
            new Tile(2549, 3574, 0),
            new Tile(2549, 3579, 0),
            new Tile(2539, 3579, 0),
            new Tile(2538, 3572, 0),
            new Tile(2540, 3571, 0),
            new Tile(2540, 3569, 0),
            new Tile(2539, 3570, 0),
            new Tile(2539, 3563, 0),
            new Tile(2545, 3562, 0),
            new Tile(2546, 3574, 0));
    @Override
    public boolean isValid() {
        return !CardState.checkedCard;
    }

    @Override
    public int onLoop() {
        WidgetChild cardInterfaceClose = getCardInterface();
        if (cardInterfaceClose != null && cardInterfaceClose.isVisible()) {
            for (Bar bar : Bar.values()) {
                if (Widgets.getWidgetChild(220,bar.getGCIndex()).getText().contains("00ff00")) {
                    bar.setVisited(true);
                }
            }
            Logger.log ("Updated list of bars crawled!");
            CardState.checkedCard = true;
            return Timing.getSleepDelay();
        }
        Item card = Inventory.get("Barcrawl card");
        if (card != null && card.isValid()) {
            if (Interaction.delayItemInteract(card, "Read")) {
                Sleep.sleepUntil(() -> {
                    WidgetChild cardInterface = getCardInterface();
                    return cardInterface != null && cardInterface.isVisible();
                }, 3000);
            }
        }
        return Timing.loopReturn();
    }

    private static WidgetChild getCardInterface() {
        return Widgets.getWidgetChild(220,16);
    }
}
