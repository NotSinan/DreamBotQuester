package org.dreambot.quests.freequests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.NPCHelper;
import org.dreambot.utilities.QuestHelper;

public class TalkToLuthasLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return SmuggleState.isOnKaramja() &&
                ((!SmuggleState.talkedLuthasBeforeBananas && Inventory.contains("Karamjan rum") && Inventory.count("Banana") >= 10) ||
                        (SmuggleState.talkedLuthasBeforeBananas && SmuggleState.stashedRum && SmuggleState.filledCrateWithBananas));
    }

    @Override
    public int onLoop() {
        if (Dialogues.inDialogue()) {
            String dialog = QuestHelper.getDialogue();
            if (dialog != null) {
                if (dialog.contains("You wouldn't believe the demand for bananas from") ||
                        dialog.contains("Have you completed your task yet?")) {
                    Logger.log("Luthas employed us - can stash rum with bananas");
                    SmuggleState.talkedLuthasBeforeBananas = true;
                }

                if (dialog.contains("Well done, here's your payment.")) {
                    Logger.log("Luthas has ended your seasonal assignment due to changing business needs - pick up rum in Sarim Food Shop");
                    SmuggleState.talkedLuthasAfterBananas = true;
                }
            }
        }

        final Area LUTHAS_AREA = new Area(2941, 3152, 2935, 3156, 0);
        final String[] DIALOGUE_OPTIONS = {"Could you offer me employment on your plantation?", "What did I have to do again?"};
        return NPCHelper.goAndTalkToNpc(LUTHAS_AREA, "Luthas", DIALOGUE_OPTIONS);
    }
}
