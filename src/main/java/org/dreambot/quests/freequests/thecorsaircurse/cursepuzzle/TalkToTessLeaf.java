package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

public class TalkToTessLeaf extends Leaf {
    private static final Area CHIEF_TESS_AREA = new Area(2007, 9010, 2018, 8999, 1);
    private final String[] DIALOGUE_OPTIONS = {"I've come to return what Arsen stole."};

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 15 &&
                        CurseState.talkedToIthoi() &&
                        CurseState.talkedToGnocci() &&
                        CurseState.talkedToArsen() &&
                        CurseState.talkedToColin() &&
                        Inventory.contains("Ogre artefact");
    }
    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(CHIEF_TESS_AREA, "Chief Tess", DIALOGUE_OPTIONS); }

    private static final Tile LADDER_TILE = new Tile(2012, 9005, 1);

    public static boolean leaveTessArea() {
        if (CHIEF_TESS_AREA.contains(Players.getLocal())) {
            GameObject vineLadder = GameObjects.closest(g -> g.hasAction("Climb") && g.getName().equals("Vine ladder"));
            if (vineLadder != null && vineLadder.exists()) {
                if (Interaction.delayEntityInteract(vineLadder, "Climb")) {
                    Sleep.sleepUntil(() -> !CHIEF_TESS_AREA.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 3000, 100);
                }
                Timing.loopReturn();
                return false;
            }
            QuestHelper.walkToTile(LADDER_TILE);
            Timing.loopReturn();
            return false;
        }
        return true;
    }

}
