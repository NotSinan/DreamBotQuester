package script.utilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankMode;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import java.awt.*;

public class API
{
    public static String currentBranch = "";
    public static String currentLeaf = "";
    public static String lastGameMsg = "";
    /**
     * Call this method before checking owned items to ensure our local DreamBot bank cache has been initialized after each script start.
     * Opens bank and calls an API method from the Bank class to initialize it.
     * @return True if bank cache is initialized
     */
    public static boolean checkedBank()
    {
        if(Bank.getLastBankHistoryCacheTime() > 0)
        {
            return true;
        }
        if(Bank.isOpen())
        {
            Bank.contains(995);
            return true;
        }
        else if(!Bank.open()) Sleep.sleep(666,1200);
        return false;
    }

    /**
     * Returns true if have item in bank and attempts to get it, otherwise returns false
     * @param ID
     * @param qty
     * @return
     */
    public static boolean withdrawItem(int ID, int qty, boolean noted)
    {
        if(!checkedBank()) return true;
        if(Bank.contains(ID))
        {
            if(Bank.open())
            {
                if(Bank.getWithdrawMode() != (noted ? BankMode.NOTE : BankMode.ITEM))
                {
                    Bank.setWithdrawMode(noted ? BankMode.NOTE : BankMode.ITEM);
                    Sleep.sleepTick();
                    return true;
                }
                if(!Inventory.isFull())
                {
                    int tmp = Inventory.count(ID);
                    if(Bank.withdraw(ID,qty))
                    {
                        Sleep.sleepUntil(() -> Inventory.count(ID) > tmp,4000,69);
                    }
                }
                else if(Bank.depositAllItems())
                {
                    Sleep.sleepUntil(() -> Inventory.isEmpty(), 4000,69);
                }
                return true;
            }
            Sleep.sleepTick();
            return true;
        }
        return false;
    }
    public static void walk(Tile destination)
    {
        if(Walking.shouldWalk(6) && Walking.walk(destination))
        {
            Sleep.sleepTick();
            Sleep.sleep(666,1222);
        }
    }
    public static void walkCenter(Area area)
    {
        walk(area.getCenter());
    }
    public static boolean walkArea(Area area)
    {
        if(!area.contains(Players.getLocal()))
        {
            walkCenter(area);
            return false;
        }
        return true;
    }
    public static boolean interactObject(String name, String action)
    {
        GameObject obj = GameObjects.closest(g -> g != null &&
                g.distance() <= 15 &&
                g.getName().equals(name) &&
                g.hasAction(action));
        if(obj != null)
        {
            if(obj.isOnScreen() && obj.interact(action))
            {
                Sleep.sleepTick();
                return true;
            }
            walk(obj.getTile());
            return true;
        }
        return false;
    }
    public static boolean interactNPC(String name, String action)
    {
        NPC npc = NPCs.closest(g -> g != null &&
                g.distance() <= 15 &&
                g.getName().equals(name) &&
                g.hasAction(action));
        if(npc != null)
        {
            if(npc.isOnScreen() && npc.canReach() && npc.interact(action))
            {
                Sleep.sleepTick();
                return true;
            }
            walk(npc.getTile());
            return true;
        }
        return false;
    }
    public static boolean talkToNPC(String name)
    {
        NPC npc = NPCs.closest(g -> g != null && g.getName().equals(name) && g.hasAction("Talk-to"));
        if(npc != null)
        {
            if(npc.distance() <= 15 &&
                    npc.isOnScreen() &&
                    npc.canReach() &&
                    npc.interact("Talk-to"))
            {
                Sleep.sleepUntil(Dialogues::inDialogue,() -> Players.getLocal().isMoving(),5000,69);
                return true;
            }
            walk(npc.getTile());
            return true;
        }
        return false;
    }

    public static void takeGroundSpawn(int ID, Tile tile)
    {
        GroundItem gItem = GroundItems.closest(g -> g != null &&
                g.distance() <= 15 &&
                g.getID() == ID &&
                g.getTile().equals(tile));
        if(gItem != null)
        {
            if(gItem.isOnScreen() && gItem.interact("Take"))
            {
                Sleep.sleepUntil(() -> Inventory.contains(ID),() -> Players.getLocal().isMoving(),5000,69);
                return;
            }
        }
        API.walk(tile);
    }
    public static void takeGroundSpawn(int ID, Area area)
    {
        GroundItem gItem = GroundItems.closest(g -> g != null &&
                g.distance() <= 15 &&
                g.getID() == ID &&
                area.contains(g));
        if(gItem != null)
        {
            if(gItem.isOnScreen() && gItem.interact("Take"))
            {
                Sleep.sleepUntil(() -> Inventory.contains(ID),() -> Players.getLocal().isMoving(),5000,69);
                return;
            }
        }
        API.walkArea(area);
    }


}
