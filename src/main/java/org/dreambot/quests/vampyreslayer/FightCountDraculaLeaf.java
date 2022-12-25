package org.dreambot.quests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class FightCountDraculaLeaf extends Leaf {

    private final Area COUNT_DRAYNOR_AREA = new Area(3075, 9778, 3080, 9768);
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 2 && Inventory.contains("Garlic", "Stake", "Hammer");
    }

    @Override
    public int onLoop() {
        if (!COUNT_DRAYNOR_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(COUNT_DRAYNOR_AREA.getRandomTile());
            }
        } else {
            if (!Players.getLocal().isInCombat()) {
                GameObject coffin = GameObjects.closest("Coffin");
                if (coffin.interact("Open")) {
                    Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
                } else {
                    NPC countDraynor = NPCs.closest("Count Draynor");
                    countDraynor.interact("Attack");
                }
            }
        }
        return Timing.loopReturn();
    }
}
