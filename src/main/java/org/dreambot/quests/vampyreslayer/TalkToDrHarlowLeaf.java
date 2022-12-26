package org.dreambot.quests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToDrHarlowLeaf extends Leaf {

    private final Area DR_HARLOW_AREA = new Area(3216, 3404, 3227, 3392);
    private final String[] DIALOGUE_OPTIONS = {"Morgan needs your help!"};
    private final String DR_HARLOW = "Dr Harlow";

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 &&
                Inventory.contains("Coins") &&
                Inventory.contains("Garlic") &&
                Inventory.contains("Beer") &&
                Inventory.contains("Hammer") || PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 2 && !Inventory.contains("Stake");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(DR_HARLOW_AREA, DR_HARLOW, DIALOGUE_OPTIONS);
    }
}
