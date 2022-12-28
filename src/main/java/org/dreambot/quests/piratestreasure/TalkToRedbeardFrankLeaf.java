package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToRedbeardFrankLeaf extends Leaf {

    private final Area RED_BEARD_FRANK_AREA = new Area(3049, 3254, 3054, 3249);
    private final String[] DIALOGUE_OPTIONS = {"I'm in search of treasure.", "Yes.", "Ok, I will bring you some rum"};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 0 && Inventory.contains("Coins");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(RED_BEARD_FRANK_AREA, "Redbeard Frank", DIALOGUE_OPTIONS);
    }
}
