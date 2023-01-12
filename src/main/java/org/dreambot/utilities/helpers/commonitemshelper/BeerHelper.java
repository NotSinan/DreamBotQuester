package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.NPCHelper;

public class BeerHelper {
    public static int getBeer() {
        final Area BARTENDER_AREA = new Area(3216, 3404, 3227, 3392);
        final String[] DIALOGUE_OPTIONS = {"A glass of your finest ale please."};
        return NPCHelper.goAndTalkToNpc(BARTENDER_AREA, "Bartender", DIALOGUE_OPTIONS);
    }
}
