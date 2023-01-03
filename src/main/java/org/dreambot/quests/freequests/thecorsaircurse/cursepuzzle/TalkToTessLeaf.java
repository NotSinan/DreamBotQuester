package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class TalkToTessLeaf extends Leaf {
    private static final Tile LADDER_TILE = new Tile(2012, 9005, 1);

    public static boolean leaveTessArea() {
        final Area CHIEF_TESS_AREA = new Area(2007, 9010, 2018, 8999, 1);
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
            Timing.getSleepDelay();
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 15 &&
                CurseState.talkedToIthoi() &&
                CurseState.talkedToGnocci() &&
                CurseState.talkedToArsen() &&
                CurseState.talkedToColin() &&
                Inventory.contains("Ogre artefact");
    }

    @Override
    public int onLoop() {
        final Area CHIEF_TESS_AREA = new Area(2007, 9010, 2018, 8999, 1);
        return QuestHelper.goAndTalkToNpc(
                CHIEF_TESS_AREA,
                "Chief Tess",
                new String[]{"I've come to return what Arsen stole."}
        );
    }

}
