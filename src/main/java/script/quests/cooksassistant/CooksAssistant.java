package script.quests.cooksassistant;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import script.utilities.API;
import script.utilities.Questz;

import java.util.List;
import java.util.Map;

public class CooksAssistant extends Questz {


    public CooksAssistant(int questPoints, Map<Skill, Integer> skills, List<Quest> quests) {
        super(questPoints, skills, quests);
    }

    @Override
    public boolean completed() {
        return getProgressValue() == 2;
    }

    @Override
    public int getProgressValue() {
        return PlayerSettings.getConfig(29);
    }


    @Override
    public boolean isValid() {
        return !this.completed() && this.reqs.checkRequirements();
    }

    private static final int EGG = 1944;
    private static final int BUCKET = 1925;
    private static final int BUCKET_OF_MILK = 1927;
    private static final int POT_OF_FLOUR = 1933;
    private static final int POT = 1931;
    private static final int GRAIN = 1947;
    private static final Area GRAIN_FIELD = new Area(
            new Tile(3159, 3294, 0),
            new Tile(3154, 3296, 0),
            new Tile(3154, 3306, 0),
            new Tile(3158, 3305, 0),
            new Tile(3163, 3299, 0),
            new Tile(3162, 3290, 0));
    private static final Area GRAIN_TOWER_3 = new Area(3164, 3309, 3169, 3304, 2);
    private static final Area GRAIN_TOWER_2 = new Area(3164, 3309, 3169, 3304, 1);
    private static final Area GRAIN_TOWER_1 = new Area(3163, 3310, 3170, 3303, 0);
    private static final Tile POT_LUMBY = new Tile(3209, 3214, 0);
    private static final Area EGG_CHIKKEN = new Area(3226, 3301, 3233, 3295, 0);
    private static final Area MILK_COW = new Area(
            new Tile(3251, 3274, 0),
            new Tile(3251, 3278, 0),
            new Tile(3256, 3274, 0),
            new Tile(3256, 3269, 0),
            new Tile(3253, 3271, 0));
    private static final Area COOK_AREA = new Area(3205, 3217, 3212, 3212, 0);
    private static final Tile BUCKET_SPAWN = new Tile(3216, 9625, 0);
    public static final int GRAIN_HOPPER_FULL_VARBIT = 4920; // value represents # of pots of flour available to take from hopper in lumby grain mill
    public static boolean haveGrainWaiting()
    {
        return PlayerSettings.getBitValue(GRAIN_HOPPER_FULL_VARBIT) > 0;
    }

    @Override
    public int onLoop() {
        switch(getProgressValue())
        {
            case(0):case(1):
            if(getSupplies())
            {
                if(!API.talkToNPC("Cook"))
                {
                    API.walkArea(COOK_AREA);
                }
            }
            break;
        }
        return Calculations.random(222,999);
    }
    public static boolean getSupplies()
    {
        if(!Inventory.contains(EGG))
        {
            if(!API.withdrawItem(EGG,1,false))
            {
                //get egg
                API.takeGroundSpawn(EGG,EGG_CHIKKEN);
                return false;
            }
            return false;
        }
        if(!Inventory.contains(POT_OF_FLOUR))
        {
            if(!API.withdrawItem(POT_OF_FLOUR,1,false))
            {
                //get flour
                if(!Inventory.contains(POT))
                {
                    //get pot
                    API.takeGroundSpawn(POT,POT_LUMBY);
                    return false;
                }
                if(haveGrainWaiting())
                {
                    //get grain that is waiting
                    if(API.interactObject("Flour bin","Empty"))
                    {
                        Sleep.sleepUntil(() -> Inventory.contains(POT_OF_FLOUR),5000,69);
                        return false;
                    }
                    API.walkArea(GRAIN_TOWER_1);
                    return false;
                }
                if(!Inventory.contains(GRAIN))
                {
                    if(GRAIN_FIELD.contains(Players.getLocal()))
                    {
                        GameObject grain = GameObjects.closest(g -> g!=null && g.getName().equals("Wheat") && g.hasAction("Pick"));
                        if(grain != null && grain.interact("Pick"))
                        {
                            Sleep.sleepUntil(() -> Inventory.contains(GRAIN),() -> Players.getLocal().isMoving(),6000,69);
                        }
                        return false;
                    }
                    if(Walking.shouldWalk(6) && Walking.walk(GRAIN_FIELD))
                    {
                        Sleep.sleep(666,1222);
                    }
                    return false;
                }
                if(API.walkArea(GRAIN_TOWER_3))
                {
                    if(API.lastGameMsg.contains("You put the grain in the hopper. You should now pull the lever nearby"))
                    {
                        if(API.interactObject("Hopper controls","Operate"))
                        {
                            Sleep.sleepUntil(() -> haveGrainWaiting(),5000,69);
                        }
                        return false;
                    }
                    if(API.interactObject("Hopper","Fill"))
                    {
                        Sleep.sleepUntil(() ->
                                        API.lastGameMsg.contains("You put the grain in the hopper. You should now pull the lever nearby"),
                                5000,69);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        if(!Inventory.contains(BUCKET_OF_MILK))
        {
            if(!API.withdrawItem(BUCKET_OF_MILK,1,false))
            {
                if(!Inventory.contains(BUCKET))
                {
                    //get bucket
                    API.takeGroundSpawn(BUCKET,BUCKET_SPAWN);
                    return false;
                }
                //fill bucket
                if(API.interactObject("Dairy cow","Milk"))
                {
                    Sleep.sleepUntil(() -> Inventory.contains(BUCKET_OF_MILK),() -> Players.getLocal().isMoving(),6000,69);
                    return false;
                }
                API.walkArea(MILK_COW);
            }
            return false;
        }
        return true;
    }

}
