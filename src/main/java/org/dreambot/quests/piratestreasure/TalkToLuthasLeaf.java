package org.dreambot.quests.piratestreasure;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToLuthasLeaf extends Leaf {

    private final Area LUTHAS_AREA = new Area(2935, 3156, 2941, 3150);
    private final String[] DIALOGUE_OPTIONS = {"Could you offer me employment on your plantation?"};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_PIRATES_TREASURE.getId()) == 1 &&
                Inventory.contains("Coins") &&
                Inventory.contains("Karamjan rum") &&
                Inventory.count("Banana") == 10;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(LUTHAS_AREA, "Luthas", DIALOGUE_OPTIONS);
    }
}
