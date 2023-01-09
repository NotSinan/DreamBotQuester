package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.WalkingHelper;

public class TalkToGertrudeLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 0 ||
                PlayerSettings.getConfig(PaidQuest.GERTRUDES_CAT.getConfigID()) == 5;
    }

    @Override
    public int onLoop() {
        Area LUMBERYARD_AREA = new Area(
                new Tile(3300, 3492, 0),
                new Tile(3305, 3492, 0),
                new Tile(3307, 3493, 0),
                new Tile(3313, 3493, 0),
                new Tile(3325, 3504, 0),
                new Tile(3326, 3514, 0),
                new Tile(3322, 3518, 0),
                new Tile(3298, 3518, 0),
                new Tile(3293, 3513, 0),
                new Tile(3293, 3504, 0),
                new Tile(3296, 3497, 0)
        );
        if (LUMBERYARD_AREA.contains(Players.getLocal())) {
            GameObject brokenFence = GameObjects.closest("Broken fence");
            if (brokenFence != null && brokenFence.canReach(new Tile(3308, 3492, 0)) && brokenFence.interact("Climb-over")) {
                Sleep.sleepUntil(() -> !Players.getLocal().isAnimating(), 3000);
                return Timing.loopReturn();
            } else {
                WalkingHelper.walkToTile(3308, 3492, 0);
                return Timing.getSleepDelay();
            }
        }

        return NPCHelper.goAndTalkToNpc(
                new Area(3147, 3412, 3157, 3408), //gertrude area
                "Gertrude",
                new String[]{"Yes."}
        );
    }
}
