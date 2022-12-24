package org.dreambot.quests.romeoandjuliet;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToRomeoLeaf extends Leaf {

    private final Area ROMEO_AREA = new Area(3205, 3438, 3223, 3422);
    private final String[] DIALOGUE_OPTIONS = {"Yes, I have seen her actually!", "Yes, ok, I'll let her know.", "Yes."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 20 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 60;
    }


    @Override
    public int onLoop() {
        if (PlayerSettings.getBitValue(12139) == 0) {
            QuestHelper.goAndTalkToNpc(ROMEO_AREA, "Romeo", DIALOGUE_OPTIONS);
            return Timing.loopReturn();
        } else {
            if (Dialogues.inDialogue()) {
                if (Dialogues.canContinue()) {
                    Dialogues.continueDialogue();
                }
            }
        }
        return Timing.loopReturn();
    }
}
