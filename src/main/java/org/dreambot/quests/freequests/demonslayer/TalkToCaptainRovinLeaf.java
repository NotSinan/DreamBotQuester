package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToCaptainRovinLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 2 &&
                !Inventory.contains(2400) &&
                PlayerSettings.getBitValue(2567) == 0;
    }

    @Override
    public int onLoop() {
        final Area CAPTAIN_ROVIN_AREA = new Area(
                new Tile[]{
                        new Tile(3200, 3496, 2),
                        new Tile(3200, 3499, 2),
                        new Tile(3202, 3501, 2),
                        new Tile(3205, 3501, 2),
                        new Tile(3207, 3499, 2),
                        new Tile(3207, 3496, 2),
                        new Tile(3205, 3494, 2),
                        new Tile(3202, 3494, 2)
                }
        );
        final String[] DIALOGUE_OPTIONS = {
                "Yes I know, but this is important.",
                "There's a demon who wants to invade this city.", "" +
                "Yes, very.",
                "It's not them who are going to fight the demon, it's me.",
                "Sir Prysin said you would give me the key.",
                "Why did he give you one of the keys then?"
        };
        final String CAPTAIN_ROVIN = "Captain Rovin";
        return QuestHelper.goAndTalkToNpc(CAPTAIN_ROVIN_AREA, CAPTAIN_ROVIN, DIALOGUE_OPTIONS);
    }
}
