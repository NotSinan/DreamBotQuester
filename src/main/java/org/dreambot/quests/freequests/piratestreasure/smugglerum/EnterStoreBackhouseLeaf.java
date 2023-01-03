package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class EnterStoreBackhouseLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return SmuggleState.haveShippedRum() && Equipment.contains("White apron");
    }

    @Override
    public int onLoop() {
        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                if (Dialogues.continueDialogue()) {
                    Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
                }
                return Timing.loopReturn();
            }
            if (Dialogues.areOptionsAvailable()) {
                if (Dialogues.chooseFirstOptionContaining("Well, can I get a job here?")) {
                    Sleep.sleepUntil(() -> Dialogues.isProcessing(), 3000);
                }
            }
            return Timing.loopReturn();
        }
        final Area FOOD_SHOP_OUTER_AREA = new Area(
                new Tile(3012, 3210, 0),
                new Tile(3013, 3210, 0),
                new Tile(3016, 3207, 0),
                new Tile(3016, 3203, 0),
                new Tile(3012, 3203, 0));
        return QuestHelper.goAndInteractWithGameObject(FOOD_SHOP_OUTER_AREA, "Door", "Open", () -> Dialogues.inDialogue());
    }
}
