package org.dreambot.utilities.helpers;

import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public class DialogueHelper {
    public static String getDialogue() {
        String txt = "";
        WidgetChild dialogWidget = Widgets.getWidgetChild(193, 2);
        if(dialogWidget == null || !dialogWidget.isVisible()) {
            dialogWidget = Widgets.getWidgetChild(231,6);
        }
        if(dialogWidget != null && dialogWidget.isVisible()) {
            txt = dialogWidget.getText();
            if(txt != null && !txt.isEmpty() && !txt.equalsIgnoreCase("null"))
            {
                Logger.log("NPC Dialogue: " + txt);
                return txt;
            }
        }
        txt = Dialogues.getNPCDialogue();
        if(txt != null && !txt.isEmpty() && !txt.equalsIgnoreCase("null")) {
            Logger.log("NPC Dialogue: " + txt);
            return txt;
        }
        return null;
    }
}
