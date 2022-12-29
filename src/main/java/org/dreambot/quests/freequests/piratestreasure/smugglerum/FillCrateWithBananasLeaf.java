package org.dreambot.quests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class FillCrateWithBananasLeaf extends Leaf {
    private final Area CRATE_AREA = new Area(
            new Tile(2936, 3151, 0),
            new Tile(2936, 3149, 0),
            new Tile(2945, 3149, 0),
            new Tile(2945, 3156, 0),
            new Tile(2942, 3156, 0),
            new Tile(2942, 3152, 0));
    @Override
    public boolean isValid() {
        return !SmuggleState.filledCrateWithBananas && Inventory.count("Banana") >= 10 && SmuggleState.isOnKaramja();
    }

    @Override
    public int onLoop() {
        if(Dialogues.inDialogue()) {
            String dialog = QuestHelper.getDialogue();
            if(dialog != null && dialog.contains("You pack all your bananas into the crate")) {
                SmuggleState.filledCrateWithBananas = true;
            }
        }
        return QuestHelper.goAndInteractWithGameObject(CRATE_AREA, "Crate", "Fill", () -> Dialogues.inDialogue());
    }
}
