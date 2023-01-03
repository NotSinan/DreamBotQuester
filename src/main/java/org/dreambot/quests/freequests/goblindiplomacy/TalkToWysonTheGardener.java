package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToWysonTheGardener extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 0 && Inventory.count("Woad leaves") != 2;
    }

    @Override
    public int onLoop() {

        if (Inventory.count("Coins") < 40) {
            return QuestHelper.withdrawFromBank("Coins", 40);
        }
        final Area WYSON_AREA = new Area(3024, 3383, 3029, 3375);
        final String[] DIALOGUE_OPTIONS = {"Yes please, I need woad leaves.", "How about 20 coins?"};
        final String WYSON = "Wyson the gardener";
        return QuestHelper.goAndTalkToNpc(WYSON_AREA, WYSON, DIALOGUE_OPTIONS);
    }
}
