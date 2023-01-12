package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;

import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.quest.book.MiniQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.DialogueHelper;
import org.dreambot.utilities.helpers.NPCHelper;
import org.dreambot.utilities.Timing;

import java.util.Arrays;

public class TalkToGrimhandBartenderLeaf extends Leaf {

    public static boolean foundLongWait = false;
    public static boolean foundWait = false;

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(MiniQuest.ALFRED_GRIMHANDS_BARCRAWL.getConfigID()) > 0 &&
                !AlfredGrimhandsBarcrawl.finishedCard;
    }

    @Override
    public int onLoop() {
        String dialog = DialogueHelper.getDialogue();
        if (dialog != null) {
            if (dialog.contains("of expensive parts to the cocktail, though, so it will cost") ||
                    dialog.contains("Ah, you'll be wanting some Ape Bite Liqueur then. It's") ||
                    dialog.contains("Haha time to be breaking out the old Supergrog") ||
                    dialog.contains("My supply of Olde Suspiciouse is starting") ||
                    dialog.contains("Fancy a bit of Heart Stopper then do you? It'll only be") ||
                    dialog.contains("contained the Liverbane Ale? That") ||
                    dialog.contains("sure the human boys will like it as well.") ||
                    dialog.contains("I suppose you'll be wanting some Fire Brandy. That'll") ||
                    dialog.contains("You're going to have to pay 50 gold for the Uncle")) {
                foundLongWait = true;
            }
            if (dialog.contains("Ah, you've come to the best stop on your list! I'll give") ||
                    dialog.contains("Ok one Black Skull Ale coming up, 8 coins please.")) {
                foundWait = true;
            }
        }

        if (Dialogues.canContinue()) {
            if (Dialogues.continueDialogue()) {
                Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
                if (foundLongWait) {
                    Sleep.sleep(10000, 12000);
                    foundLongWait = false;
                }
                if (foundWait) {
                    Sleep.sleep(5000, 7000);
                    foundWait = false;
                }
            }
            return Timing.loopReturn();
        }

        if (Dialogues.areOptionsAvailable()) {
            if (Dialogues.chooseFirstOptionContaining("I'm doing Alfred Grimhand")) {
                Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
            }
            return Timing.loopReturn();
        }
        Arrays.stream(Bar.values()).filter(bar -> bar.visited()).findFirst().ifPresent(bar -> {
            NPCHelper.goAndTalkToNpc(bar.getArea(), bar.getBartender(), new String[]{"I'm doing Alfred Grimhand's barcrawl.", "I'm doing Alfred Grimhands Barcrawl."});
        });
        return Timing.loopReturn();
    }

}
