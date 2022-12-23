package org.dreambot.quests.sheepshearer;

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

public class TalkToFredLeaf extends Leaf {

    private final Area FARMER_FRED_FARM_AREA = new Area(3183, 3280, 3192, 3270);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(179) == 0 ||
                PlayerSettings.getConfig(179) == 1 && Inventory.count("Ball of wool") >= 20 ||
                PlayerSettings.getConfig(179) == 20;
    }

    @Override
    public int onLoop() {

        if (!FARMER_FRED_FARM_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(FARMER_FRED_FARM_AREA.getRandomTile());
            }
        } else {
            if (!Dialogues.inDialogue()) {
                NPC fred = NPCs.closest(npc -> npc != null && npc.getName().equals("Fred the Farmer"));
                fred.interact("Talk-to");
                Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
            } else {
                if (Dialogues.areOptionsAvailable()) {
                    Dialogues.chooseFirstOption("I'm looking for a quest.",
                            "Yes, okay. I can do that.",
                            "Yes.",
                            "I need to talk to you about shearing these sheep!");
                    Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                } else {
                    if (Dialogues.canContinue()) {
                        Dialogues.continueDialogue();
                        Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                    }
                }
            }
        }
        return 400;
    }
}
