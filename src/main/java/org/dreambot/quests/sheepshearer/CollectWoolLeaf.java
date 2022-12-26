package org.dreambot.quests.sheepshearer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class CollectWoolLeaf extends Leaf {
    private final Area SHEEP_AREA = new Area(3193, 3276, 3211, 3257);
    private final Tile SHEARS_SPAWN = new Tile(3192, 3272, 0);
    @Override
    public boolean isValid() {
        //collect more wool if total wool count less than required
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_SHEEP_SHEARER.getId()) == 1 &&
                Inventory.count(i -> i.getName().equals("Wool") || i.getName().equals("Ball of wool")) < 20;
    }

    @Override
    public int onLoop() {
        if (!Inventory.contains("Shears")) {
            return QuestHelper.pickupGroundSpawn(SHEARS_SPAWN, "Shears");
        }
        if(QuestHelper.walkToArea(SHEEP_AREA)) {
            NPC sheep = NPCs.closest(npc -> npc.getName().equals("Sheep") && npc.hasAction("Shear"));
            if (sheep != null && Interaction.delayEntityInteract(sheep, "Shear")) {
                int count = Inventory.count("Wool");
                Sleep.sleepUntil(() -> Inventory.count("Wool") > count, () -> Players.getLocal().isMoving(), 3000, 100);
            }
        }
        return Timing.loopReturn();
    }
}