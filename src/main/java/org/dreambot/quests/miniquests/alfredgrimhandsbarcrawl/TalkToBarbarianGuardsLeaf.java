package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.MiniQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.NPCHelper;

public class TalkToBarbarianGuardsLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(MiniQuest.ALFRED_GRIMHANDS_BARCRAWL.getConfigID()) == 0 ||
                AlfredGrimhandsBarcrawl.finishedCard;
    }

    @Override
    public int onLoop() {

        final Area BARBARIAN_GUARDS_AREA = new Area(
                new Tile(2549, 3574, 0),
                new Tile(2549, 3579, 0),
                new Tile(2539, 3579, 0),
                new Tile(2538, 3572, 0),
                new Tile(2540, 3571, 0),
                new Tile(2540, 3569, 0),
                new Tile(2539, 3570, 0),
                new Tile(2539, 3563, 0),
                new Tile(2545, 3562, 0),
                new Tile(2546, 3574, 0)
        );
        return NPCHelper.goAndTalkToNpc(
                BARBARIAN_GUARDS_AREA,
                "Barbarian guard",
                new String[]{
                        "I want to come through this gate.",
                        "Looks can be deceiving, I am in fact a barbarian.",
                        "Sorry, I'm a bit busy.",
                        "Yes please, I want to smash my vials."
                });
    }
}
