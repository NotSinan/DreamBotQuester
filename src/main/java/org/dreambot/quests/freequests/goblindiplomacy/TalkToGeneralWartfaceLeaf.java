package org.dreambot.quests.goblindiplomacy;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToGeneralWartfaceLeaf extends Leaf {

    private final Area GENERAL_WARTFACE_AREA = new Area(
            new Tile[] {
                    new Tile(2954, 3510, 0),
                    new Tile(2961, 3510, 0),
                    new Tile(2961, 3514, 0),
                    new Tile(2957, 3514, 0),
                    new Tile(2954, 3512, 0)
            }
    );
    private final String[] DIALOGUE_OPTIONS = {
            "So how is life for the goblins?",
            "Yes, Wartface looks fat",
            "Do you want me to pick an armour colour for you?",
            "What about a different colour?",
            "I have some orange armour here"
    };

    private final String GENERAL_WARTFACE = "General Wartface";

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_GOBLIN_DIPLOMACY.getId()) == 0 &&
                Inventory.count("Woad leaves") == 2 &&
                Inventory.count("Onion") == 2 &&
                Inventory.count("Redberries") == 3;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(GENERAL_WARTFACE_AREA, GENERAL_WARTFACE, DIALOGUE_OPTIONS);
    }
}
