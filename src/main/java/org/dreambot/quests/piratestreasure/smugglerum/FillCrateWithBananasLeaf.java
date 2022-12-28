package org.dreambot.quests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class FillCrateWithBananasLeaf extends Leaf {
    private final Area CRATE_AREA = new Area(2938, 3155, 2944, 3150, 0);
    private final String LUTHAS_NAME = "Luthas";
    private final String[] DIALOGUE_OPTIONS = {"Could you offer me employment on your plantation?"};
    @Override
    public boolean isValid() {
        return SmuggleState.atStart && Inventory.contains("Karamjan rum") && SmuggleState.isOnKaramja();
    }

    @Override
    public int onLoop() {
        if(Dialogues.inDialogue()) {
            String dialog = QuestHelper.getDialogue();
            if(dialog != null) {
                if(dialog.contains("You wouldn't believe the demand for bananas from")) {
                    SmuggleState.talkedLuthasBeforeBananas = true;
                }
            }
        }
        

        return QuestHelper.goAndTalkToNpc(LUTHAS_AREA, "Luthas", DIALOGUE_OPTIONS);
    }
}
