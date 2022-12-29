package org.dreambot.quests.cooksassistant;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrievePotOfFlourLeaf extends Leaf {

    public static boolean HOPPER_LOADED = false; //flips to true upon putting grain in hopper

    private final int GRAIN_TOWER_FLOUR_COUNT_VARBIT = 4920; //Indicates the quantity of flour available to take from bin in west lumbridge grain tower
    private final Tile POT_LUMBRIDGE_SPAWN = new Tile(3209, 3214, 0);
    private final Tile BUCKET_LUMBRIDGE_SPAWN = new Tile(3216, 9625, 0);
    private final Area WHEAT_FIELD_AREA = new Area(
            new Tile(3161, 3293, 0),
            new Tile(3155, 3297, 0),
            new Tile(3154, 3305, 0),
            new Tile(3158, 3304, 0),
            new Tile(3162, 3299, 0));
    private final Area GRAIN_TOWER_LVL1_AREA = new Area(
            new Tile(3168, 3303, 0),
            new Tile(3166, 3303, 0),
            new Tile(3163, 3306, 0),
            new Tile(3166, 3310, 0),
            new Tile(3167, 3310, 0),
            new Tile(3170, 3307, 0),
            new Tile(3170, 3306, 0));
    private final Area GRAIN_TOWER_LVL3_AREA = new Area(3164, 3309, 3169, 3304, 2);
    @Override
    public boolean isValid() {
        return (PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_COOKS_ASSISTANT.getId()) == 1) && !Inventory.contains("Pot of flour");
    }


    @Override
    public int onLoop() {
        if(!Inventory.contains("Pot of flour")) {
            if(!Inventory.contains("Pot")) {
                //get bucket and pot together if not have either, because they are so close together, to save a trip
                if(!Inventory.contains("Bucket")) {
                    return QuestHelper.pickupGroundSpawn(BUCKET_LUMBRIDGE_SPAWN,"Bucket");
                }
                return QuestHelper.pickupGroundSpawn(POT_LUMBRIDGE_SPAWN,"Pot");
            }

            if(!Inventory.contains("Grain")) {
                if(!WHEAT_FIELD_AREA.contains(Players.getLocal()))
                {
                    if(Walking.shouldWalk(6)) {
                        Interaction.delayWalk(WHEAT_FIELD_AREA.getRandomTile());
                    }
                    return Timing.loopReturn();
                }

                GameObject grain = GameObjects.closest(g -> g.getName().equals("Wheat") && g.hasAction("Pick"));
                if(grain != null && Interaction.delayEntityInteract(grain,"Pick")) {
                    Sleep.sleepUntil(() -> Inventory.contains("Grain"), 3000);
                }

                return Timing.loopReturn();
            }

            if(PlayerSettings.getBitValue(GRAIN_TOWER_FLOUR_COUNT_VARBIT) > 0) {
                if(!GRAIN_TOWER_LVL1_AREA.contains(Players.getLocal())) {
                    if(Walking.shouldWalk(6)) {
                        Interaction.delayWalk(GRAIN_TOWER_LVL1_AREA.getRandomTile());
                    }
                    return Timing.loopReturn();
                }

                GameObject bin = GameObjects.closest(g -> g.hasAction("Empty") && g.getName().equals("Flour bin"));
                if(bin != null && Interaction.delayEntityInteract(bin, "Empty")) {
                    Sleep.sleepUntil(() -> Inventory.contains("Pot of flour"), 3000);
                }

                return Timing.loopReturn();
            }

            if(!GRAIN_TOWER_LVL3_AREA.contains(Players.getLocal())) {
                if(Walking.shouldWalk(6)) {
                    Interaction.delayWalk(GRAIN_TOWER_LVL3_AREA.getRandomTile());
                }
                return Timing.loopReturn();
            }

            if(!HOPPER_LOADED) {
                GameObject hopper = GameObjects.closest(g -> g.hasAction("Fill") && g.getName().equals("Hopper"));
                if(hopper != null && Interaction.delayEntityInteract(hopper, "Fill")) {
                    int count = Inventory.count("Grain");
                    Sleep.sleepUntil(() -> Inventory.count("Grain") < count, () -> Players.getLocal().isMoving(), 3000, 100);
                    HOPPER_LOADED = true;
                }
                return Timing.loopReturn();
            }

            GameObject lever = GameObjects.closest(g -> g.hasAction("Operate") && g.getName().equals("Hopper controls"));
            if(lever != null && Interaction.delayEntityInteract(lever , "Operate")) {
                Sleep.sleepUntil(() -> PlayerSettings.getBitValue(GRAIN_TOWER_FLOUR_COUNT_VARBIT) > 0, () -> Players.getLocal().isMoving(), 3000, 100);
            }
        }
        return Timing.loopReturn();
    }

}
