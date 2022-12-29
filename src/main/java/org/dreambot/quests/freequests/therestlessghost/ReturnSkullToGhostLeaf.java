package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class ReturnSkullToGhostLeaf extends Leaf {

    private final Area GHOST_AREA = new Area(3247, 3195, 3252, 3190);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 4 && Inventory.contains("Ghost's skull");
    }

    @Override
    public int onLoop() {
        if (QuestHelper.walkToArea(GHOST_AREA)) {
            GameObject coffin = GameObjects.closest("Coffin");
            if(coffin != null) {
                if (coffin.hasAction("Open")) {
                    if(Interaction.delayEntityInteract(coffin, "Open")) {
                        Sleep.sleepUntil(() -> !coffin.exists(), () -> Players.getLocal().isMoving(), 3000, 100);
                    }
                    return Timing.loopReturn();
                }
                Item skull = Inventory.get("Ghost's skull");
                if(skull != null && skull.isValid() && Interaction.delayUseItemOn(skull, coffin)) {
                    Sleep.sleepUntil(() -> !Inventory.contains("Ghost's skull"), 3000);
                }
            }
        }
        return Timing.loopReturn();
    }
}
