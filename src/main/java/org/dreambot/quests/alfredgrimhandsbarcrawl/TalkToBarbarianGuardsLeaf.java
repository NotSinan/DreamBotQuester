package org.dreambot.quests.alfredgrimhandsbarcrawl;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToBarbarianGuardsLeaf extends Leaf {
    private final Area BARBARIAN_GUARDS_AREA = new Area(
            new Tile(2549, 3574, 0),
            new Tile(2549, 3579, 0),
            new Tile(2539, 3579, 0),
            new Tile(2538, 3572, 0),
            new Tile(2540, 3571, 0),
            new Tile(2540, 3569, 0),
            new Tile(2539, 3570, 0),
            new Tile(2539, 3563, 0),
            new Tile(2545, 3562, 0),
            new Tile(2546, 3574, 0));
    private final String BARBARIAN_GUARD_NAME = "Barbarian guard";
    private final String[] DIALOGUE_OPTIONS = {"I want to come through this gate.", "Looks can be deceiving, I am in fact a barbarian.",
            "Sorry, I'm a bit busy.", "Yes please, I want to smash my vials."};


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_ALFRED_GRIMHANDS_BARCRAWL_STATE_76.getId()) == 0 ||
                CardState.finishedCard;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(BARBARIAN_GUARDS_AREA, BARBARIAN_GUARD_NAME, DIALOGUE_OPTIONS);
    }
}
