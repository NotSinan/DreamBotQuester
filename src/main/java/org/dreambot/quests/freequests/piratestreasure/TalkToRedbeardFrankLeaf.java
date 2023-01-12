package org.dreambot.quests.freequests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.piratestreasure.smugglerum.SmuggleState;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToRedbeardFrankLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 0 && Inventory.contains("Coins") ||
                PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 1 && !SmuggleState.isOnKaramja() && Inventory.contains("Karamjan rum") ||
                PlayerSettings.getConfig(FreeQuest.PIRATES_TREASURE.getConfigID()) == 2 && !Inventory.contains("Chest key");
    }

    @Override
    public int onLoop() {
        final Area RED_BEARD_FRANK_AREA = new Area(3049, 3254, 3054, 3249);
        final String[] DIALOGUE_OPTIONS = {"I'm in search of treasure.", "Yes.", "Ok, I will bring you some rum"};
        return NPCHelper.goAndTalkToNpc(RED_BEARD_FRANK_AREA, "Redbeard Frank", DIALOGUE_OPTIONS);
    }
}
