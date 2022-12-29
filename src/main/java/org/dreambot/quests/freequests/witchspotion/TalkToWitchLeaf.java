package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToWitchLeaf extends Leaf {
    private final Area WITCH_AREA = new Area(2965, 3208, 2970, 3203, 0);
    private final String WITCH_NAME = "Hetty";
    private final String[] DIALOGUE_OPTIONS = {"Yes, help me become one with my darker side.", "I am in search of a quest.", "Yes."};
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_WITCHS_POTION.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_WITCHS_POTION.getId()) == 1 && Inventory.containsAll("Onion","Rat's tail","Eye of newt","Burnt meat");
    }


    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(WITCH_AREA,WITCH_NAME, DIALOGUE_OPTIONS);
    }
}
