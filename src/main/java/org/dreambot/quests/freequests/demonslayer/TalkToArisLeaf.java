package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.Client;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class TalkToArisLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 0 && Inventory.contains("Coins");
    }

    @Override
    public int onLoop() {
        if (Client.isInCutscene()) {
            if (Dialogues.inDialogue()) {
                if (Dialogues.canContinue()) {
                    Dialogues.continueDialogue();
                    return Timing.loopReturn();
                }
            }
        }
        final Area ARIS_AREA = new Area(3200, 3427, 3206, 3421);
        final String[] DIALOGUE_OPTIONS = {"Yes.", "Ok, here you go.", "Okay, where is he? I'll kill him for you!", "So how did Wally kill Delrith?"};
        final String ARIS = "Aris";
        return QuestHelper.goAndTalkToNpc(ARIS_AREA, ARIS, DIALOGUE_OPTIONS);
    }
}
