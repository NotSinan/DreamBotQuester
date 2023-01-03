package org.dreambot.quests.freequests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToAuburyLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 3 && Inventory.contains("Research package") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 4 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 5 && !Inventory.contains("Research notes");
    }

    @Override
    public int onLoop() {
        final Area AUBURY_AREA = new Area(3250, 3402, 3255, 3400);
        final String AUBURY_NAME = "Aubury";
        final String[] DIALOGUE_OPTIONS = {"I've been sent here with a package for you."};
        return QuestHelper.goAndTalkToNpc(AUBURY_AREA, AUBURY_NAME, DIALOGUE_OPTIONS);
    }
}
