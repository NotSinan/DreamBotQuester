package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.NPCHelper;

public class RedDyeHelper implements CommonItemsHelper{
    @Override
    public int getItem() {
        Area AGGIE_AREA = new Area(3083, 3261, 3088, 3256);
        final String[] DIALOGUE_OPTIONS = {
                "Can you make dyes for me please?",
                "What do you need to make red dye?",
                "Okay, make me some red dye please."
        };
        return NPCHelper.goAndTalkToNpc(AGGIE_AREA, "Aggie", DIALOGUE_OPTIONS);
    }
}
