package org.dreambot.utilities.helpers.commonitemshelper;

import org.dreambot.api.methods.map.Area;
import org.dreambot.utilities.helpers.NPCHelper;

public class WoadLeafHelper implements CommonItemsHelper {
    @Override
    public int getItem() {
        final Area WYSON_AREA = new Area(3024, 3383, 3029, 3375);
        final String[] DIALOGUE_OPTIONS = {"Yes please, I need woad leaves.", "How about 20 coins?"};
        return NPCHelper.goAndTalkToNpc(WYSON_AREA, "Wyson the gardener", DIALOGUE_OPTIONS);
    }
}
