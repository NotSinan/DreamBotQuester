package org.dreambot.quests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToLuthasLeaf extends Leaf {
    private final Area LUTHAS_AREA = new Area(2941, 3152, 2935, 3156, 0);
    private final String LUTHAS_NAME = "Luthas";
    private final String[] DIALOGUE_OPTIONS = {"Could you offer me employment on your plantation?", "What did I have to do again?"};
    @Override
    public boolean isValid() {
        return SmuggleState.isOnKaramja() &&
                ((!SmuggleState.talkedLuthasBeforeBananas && Inventory.contains("Karamjan rum") && Inventory.count("Banana") >= 10) ||
                    (SmuggleState.talkedLuthasBeforeBananas && SmuggleState.stashedRum && SmuggleState.filledCrateWithBananas));
    }

    @Override
    public int onLoop() {
        if(Dialogues.inDialogue()) {
            String dialog = QuestHelper.getDialogue();
            if(dialog != null) {
                if(dialog.contains("You wouldn't believe the demand for bananas from") ||
                        dialog.contains("Have you completed your task yet?")) {
                    Logger.log("Luthas employed us - can stash rum with bananas");
                    SmuggleState.talkedLuthasBeforeBananas = true;
                }

                if(dialog.contains("Well done, here's your payment.")) {
                    Logger.log("Luthas has ended your seasonal assignment due to changing business needs - pick up rum in Sarim Food Shop");
                    SmuggleState.talkedLuthasAfterBananas = true;
                }
            }
        }

        return QuestHelper.goAndTalkToNpc(LUTHAS_AREA, "Luthas", DIALOGUE_OPTIONS);
    }
}
