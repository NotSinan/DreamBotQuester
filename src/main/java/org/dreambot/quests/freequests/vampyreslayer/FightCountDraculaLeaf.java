package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class FightCountDraculaLeaf extends Leaf {

    private final Area DRAYNOR_STAIRS_TO_COUNT_AREA = new Area(3119, 3354, 3113, 3363);
    private final Area COUNT_DRAYNOR_AREA = new Area(3075, 9778, 3080, 9768);
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 2 && Inventory.contains("Garlic", "Stake", "Hammer");
    }

    @Override
    public int onLoop() {
        if (COUNT_DRAYNOR_AREA.contains(Players.getLocal())) {
            if (!Players.getLocal().isInCombat()) {
                GameObject coffin = GameObjects.closest("Coffin");

                if (coffin != null && Interaction.delayEntityInteract(coffin, "Open") &&
                        Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), () -> Players.getLocal().isMoving(), 3000, 100)) {
                    return Timing.loopReturn();
                }
                NPC countDraynor = NPCs.closest("Count Draynor");
                if (countDraynor != null && Interaction.delayEntityInteract(countDraynor, "Attack")) {
                    Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), () -> Players.getLocal().isMoving(), 3000, 100);
                }
            }
            return Timing.loopReturn();
        }
        if (QuestHelper.walkToArea(DRAYNOR_STAIRS_TO_COUNT_AREA)) {
            GameObject stairs = GameObjects.closest(g -> g.hasAction("Walk-Down") && g.getName().equals("Stairs"));
            if(stairs != null && stairs.exists()) {
                if(Interaction.delayEntityInteract(stairs, "Walk-Down")) {
                    Sleep.sleepUntil(() -> COUNT_DRAYNOR_AREA.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 3000, 100);
                }
            }
        }
        return Timing.loopReturn();
    }
}
