package org.dreambot.quests.romeoandjuliet;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToJulietLeaf extends Leaf {

    private final Area JULIET_AREA = new Area(3155, 3426, 3161, 3425, 1);
    private final String[] DIALOGUE_OPTIONS = {"Ok, thanks."};


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 10 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 50 && Inventory.contains("Cadava potion") ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 50 && PlayerSettings.getBitValue(12139) == 1;
    }

    @Override
    public int onLoop() {
        if (PlayerSettings.getBitValue(12139) == 0) {
            return QuestHelper.goAndTalkToNpc(JULIET_AREA, "Juliet", DIALOGUE_OPTIONS);
        }
        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Timing.sleepForDelay();
                Dialogues.continueDialogue();
            }
        }
        return Timing.loopReturn();
    }
}
