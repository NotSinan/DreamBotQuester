package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.WalkingHelper;

public class StashRumLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return !SmuggleState.stashedRum && Inventory.contains("Karamjan rum") && SmuggleState.isOnKaramja();
    }

    @Override
    public int onLoop() {

        if (Dialogues.canContinue()) {
            String dialog = QuestHelper.getDialogue();
            if (dialog != null && dialog.contains("You stash the rum in the crate")) {
                SmuggleState.stashedRum = true;
            }
            if (Dialogues.continueDialogue())
                return Timing.loopReturn();
            return Timing.getSleepDelay();
        }
        final Area CRATE_AREA = new Area(
                new Tile(2936, 3151, 0),
                new Tile(2936, 3149, 0),
                new Tile(2945, 3149, 0),
                new Tile(2945, 3156, 0),
                new Tile(2942, 3156, 0),
                new Tile(2942, 3152, 0));
        if (!WalkingHelper.walkToArea(CRATE_AREA)) {
            return Timing.loopReturn();
        }
        GameObject crate = GameObjects.closest(g -> CRATE_AREA.contains(g) && g.getName().equals("Crate"));
        Item karamjanRum = Inventory.get("Karamjan rum");
        if (crate != null && crate.exists() && karamjanRum != null && karamjanRum.isValid() &&
                Interaction.delayUseItemOn(karamjanRum, crate)) {
            Sleep.sleepUntil(() -> Dialogues.canContinue(), () -> Players.getLocal().isMoving(), 3000, 100);
        }
        return Timing.loopReturn();
    }
}
