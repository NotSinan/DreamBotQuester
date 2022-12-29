package org.dreambot.quests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToLumbridgeDukeLeaf extends Leaf {

    private final Area LUMBRIDGE_DUKE_AREA = new Area(3208, 3225, 3213, 3218, 1);
    private final String DUKE_NAME = "Duke Horacio";
    private final String[] DIALOGUE_OPTIONS = {"Have you any quests for me?", "Sure, no problem", "Yes."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 1 && !Inventory.contains("Air talisman");
    }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(LUMBRIDGE_DUKE_AREA, DUKE_NAME, DIALOGUE_OPTIONS); }
}
