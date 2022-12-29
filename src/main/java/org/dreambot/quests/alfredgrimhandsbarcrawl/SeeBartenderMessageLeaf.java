package org.dreambot.quests.alfredgrimhandsbarcrawl;

import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

public class SeeBartenderMessageLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return API.lastGameMessage.contains("The barmaid signs your card.") ||
                API.lastGameMessage.contains("The bartender signs your card.") ||
                API.lastGameMessage.contains("Zambo signs your card.") ||
                API.lastGameMessage.contains("Blurberry signs your card.") ||
                API.lastGameMessage.contains("You think you see 2 bartenders signing 2 barcrawl cards.") ||
                API.lastGameMessage.contains("signing your barcrawl card") ||
                API.lastGameMessage.contains("The bartender scrawls his signature on your card.") ||
                API.lastGameMessage.contains("You can just about make out the bartender");
    }

    @Override
    public int onLoop() {
        CardState.checkedCard = false;
        API.lastGameMessage = "";
        return Timing.loopReturn();
    }
}
