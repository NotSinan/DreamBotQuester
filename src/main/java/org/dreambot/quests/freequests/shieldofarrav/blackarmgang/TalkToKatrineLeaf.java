package org.dreambot.quests.freequests.shieldofarrav.blackarmgang;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class TalkToKatrineLeaf extends Leaf {


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.SHIELD_OF_ARRAV.getVarBitID()) == 1;
    }

    @Override
    public int onLoop() {
        Area KATRINE_AREA = new Area(3182, 3387, 3189, 3382);
        String[] DIALOGUE_OPTIONS = {
                "I've heard you're the Black Arm Gang.",
                "I'd rather not reveal my sources.",
                "I want to become a member of your gang.",
                "Well, you can give me a try can't you?",
                "Ok, no problem."
        };
        String KATRINE = "Katrine";
        return NPCHelper.goAndTalkToNpc(KATRINE_AREA, KATRINE, DIALOGUE_OPTIONS);
    }
}
