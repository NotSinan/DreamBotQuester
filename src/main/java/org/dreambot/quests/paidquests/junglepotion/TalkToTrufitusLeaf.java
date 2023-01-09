package org.dreambot.quests.paidquests.junglepotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToTrufitusLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 0 ||
                PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 1 && Inventory.contains("Snake weed") ||
                PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 4 && Inventory.contains("Ardrigal") ||
                PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 6 && Inventory.contains("Sito foil") ||
                PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 8 && Inventory.contains("Volencia moss") ||
                PlayerSettings.getConfig(PaidQuest.JUNGLE_POTION.getConfigID()) == 10 && Inventory.contains("Rogue's purse");
    }

    @Override
    public int onLoop() {
        return NPCHelper.goAndTalkToNpc(
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
