package org.dreambot.quests.freequests.romeoandjuliet;

import org.dreambot.api.Client;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.NPCHelper;
import org.dreambot.utilities.Timing;

public class TalkToRomeoLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.ROMEO_AND_JULIET.getConfigID()) == 0 ||
                PlayerSettings.getConfig(FreeQuest.ROMEO_AND_JULIET.getConfigID()) == 20 ||
                PlayerSettings.getConfig(FreeQuest.ROMEO_AND_JULIET.getConfigID()) == 60;
    }


    @Override
    public int onLoop() {
        final Area ROMEO_AREA = new Area(3205, 3438, 3223, 3422);
        final String[] DIALOGUE_OPTIONS = {"Yes, I have seen her actually!", "Yes, ok, I'll let her know.", "Yes."};
        if (!Client.isInCutscene()) {
            return NPCHelper.goAndTalkToNpc(ROMEO_AREA, "Romeo", DIALOGUE_OPTIONS);
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
