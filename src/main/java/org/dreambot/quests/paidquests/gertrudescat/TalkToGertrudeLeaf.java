package org.dreambot.quests.gertrudescat;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToGertrudeLeaf extends Leaf {

    private final Area GERTRUDE_AREA = new Area(3147, 3412, 3157, 3408);
    private final String[] DIALOGUE_OPTIONS = {"Yes."};
    private final String GERTRUDE = "Gertrude";

    Area LUMBERYARD_AREA = new Area(
            new Tile[] {
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
            }
    );

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 5;
    }

    @Override
    public int onLoop() {

        if (LUMBERYARD_AREA.contains(Players.getLocal())) {
            GameObject brokenFence = GameObjects.closest("Broken fence");
            if (brokenFence != null && brokenFence.canReach(new Tile(3308, 3492, 0)) && brokenFence.interact("Climb-over")) {
                Sleep.sleepUntil(() -> !Players.getLocal().isAnimating(), 3000);
                return Timing.loopReturn();
            } else {
                Walking.walk(3308, 3492, 0);
            }
        }

        return QuestHelper.goAndTalkToNpc(GERTRUDE_AREA, GERTRUDE, DIALOGUE_OPTIONS);
    }
}
