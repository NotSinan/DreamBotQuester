package org.dreambot.quests.romeoandjuliet;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class TalkToApothecary extends Leaf {
    private final Area APOTHECARY_AREA = new Area(3192, 3406, 3198, 3402);
    private final Area CADAVA_BUSH_AREA = new Area(3264, 3374, 3279, 3370);
    private final String[] DIALOGUE_OPTIONS = {"Talk about something else.", "Talk about Romeo & Juliet."};


    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 40 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_ROMEO_AND_JULIET.getId()) == 50 && !Inventory.contains("Cadava potion");
    }

    @Override
    public int onLoop() {

        if (Inventory.contains("Cadava berries")) {
            return QuestHelper.goAndTalkToNpc(APOTHECARY_AREA, "Apothecary", DIALOGUE_OPTIONS);
        }

        if (!CADAVA_BUSH_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Interaction.delayWalk(CADAVA_BUSH_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        GameObject cadavaBush = GameObjects.closest("Cadava bush");
        if (cadavaBush != null && Interaction.delayEntityInteract(cadavaBush, "Pick-from")) {
            Sleep.sleepUntil(() -> Inventory.contains("Cadava berries"), 3000);
        }
        return Timing.loopReturn();
    }
}
