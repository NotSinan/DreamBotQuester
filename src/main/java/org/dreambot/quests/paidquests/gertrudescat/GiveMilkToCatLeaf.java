package org.dreambot.quests.paidquests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class GiveMilkToCatLeaf extends Leaf {

    private final Area CAT_AREA = new Area(3305, 3513, 3312, 3507, 1);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 2 && Inventory.contains("Bucket of milk");
    }

    @Override
    public int onLoop() {

        if (!CAT_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(CAT_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        NPC cat = NPCs.closest("Gertrude's cat");
        if (cat != null && Inventory.interact("Bucket of milk", "Use")) {
            cat.interact("Use");
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
        }
        return Timing.loopReturn();
    }
}
