package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToGeneralWartfaceLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 0 &&
                Inventory.count("Woad leaves") == 2 &&
                Inventory.count("Onion") == 2 &&
                Inventory.count("Redberries") == 3;
    }

    @Override
    public int onLoop() {
        final Area GENERAL_WARTFACE_AREA = new Area(
                new Tile[]{
                        new Tile(2954, 3510, 0),
                        new Tile(2961, 3510, 0),
                        new Tile(2961, 3514, 0),
                        new Tile(2957, 3514, 0),
                        new Tile(2954, 3512, 0)
                }
        );
        final String[] DIALOGUE_OPTIONS = {
                "So how is life for the goblins?",
                "Yes, Wartface looks fat",
                "Do you want me to pick an armour colour for you?",
                "What about a different colour?",
                "I have some orange armour here"
        };
        return NPCHelper.goAndTalkToNpc(GENERAL_WARTFACE_AREA, "General Wartface", DIALOGUE_OPTIONS);
    }
}
