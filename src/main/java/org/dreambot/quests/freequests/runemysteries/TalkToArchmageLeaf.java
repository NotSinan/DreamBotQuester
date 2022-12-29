package org.dreambot.quests.freequests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToArchmageLeaf extends Leaf {

    private final Area WIZARD_TOWER_AREA = new Area(3099, 9574, 3107, 9569);
    private final String ARCHMAGE_SEDRIDOR_NAME = "Archmage Sedridor";
    private final String[] DIALOGUE_OPTIONS = {"I'm looking for the head wizard.", "Okay, here you are.", "Go ahead.", "Yes, certainly."};
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 1 && Inventory.contains("Air talisman") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 2 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 3 && !Inventory.contains("Research package") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 5 && Inventory.contains("Research notes");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(WIZARD_TOWER_AREA, ARCHMAGE_SEDRIDOR_NAME, DIALOGUE_OPTIONS);
    }
}
