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
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToRomeoLeaf extends Leaf {

    private final Area VARROCK_SHOP_AREA = new Area(3209, 3421, 3223, 3409);
    private final Area ROMEO_AREA = new Area(3205, 3438, 3223, 3422);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 0;
    }

    @Override
    public int onLoop() {
        if (!ROMEO_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(ROMEO_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            NPC romeo = NPCs.closest(npc -> npc.getName().equals("Romeo"));
            if (romeo != null && romeo.interact("Talk-to")) {
                Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
            }
            return Timing.loopReturn();
        }

        if (Dialogues.canContinue()) {
            Dialogues.continueDialogue();
            Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
            return Timing.loopReturn();
        }

//        if (Dialogues.areOptionsAvailable()) {
//            Dialogues.chooseFirstOption("")
//        }

        return Timing.loopReturn();

    }
}
