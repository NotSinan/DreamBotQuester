package org.dreambot.quests.paidquests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToTrufitusLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 1 && Inventory.contains("Snake weed") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 4 && Inventory.contains("Ardrigal") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 6 && Inventory.contains("Sito foil") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 8 && Inventory.contains("Volencia moss") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_JUNGLE_POTION.getId()) == 10 && Inventory.contains("Rogue's purse");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(2805, 3089, 2814, 3082),
                "Trufitis",
                new String[]{
                        "It's a nice village, where is everyone?",
                        "Me? How can I help?",
                        "It sounds like just the challenge for me.",
                        "Yes.",
                        "Of course!"
                }
        );
    }
}
