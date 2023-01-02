package org.dreambot.quests.freequests.witchspotion;

import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class DrinkFromCauldronLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_WITCHS_POTION.getId()) == 2;
    }


    @Override
    public int onLoop() {
        final Area WITCH_AREA = new Area(2965, 3208, 2970, 3203, 0);
        if (!QuestHelper.walkToArea(WITCH_AREA)) {
            return Timing.loopReturn();
        }

        if (Dialogues.canContinue()) {
            if (Dialogues.continueDialogue()) {
                Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
            }
            return Timing.loopReturn();
        }
        GameObject cauldron = GameObjects.closest("Cauldron");
        if (cauldron != null && cauldron.exists() && Interaction.delayEntityInteract(cauldron, "Drink From")) {
            Sleep.sleepUntil(() -> Dialogues.canContinue(), () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
