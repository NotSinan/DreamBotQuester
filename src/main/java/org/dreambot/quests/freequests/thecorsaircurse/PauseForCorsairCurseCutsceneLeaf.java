package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.Client;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

public class PauseForCorsairCurseCutsceneLeaf extends Leaf {

    /**
     * Pause for cutscene during Corsair Curse, do nothing.
     * @return
     */

    @Override
    public boolean isValid() { return Client.isInCutscene(); }

    @Override
    public int onLoop() {
        if(Dialogues.canContinue()) {
            Timing.sleepForDelay();
            Dialogues.continueDialogue();
        }
        return Timing.loopReturn(); }
}
