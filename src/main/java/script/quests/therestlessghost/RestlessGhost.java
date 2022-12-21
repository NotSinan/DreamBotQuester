package script.quests.therestlessghost;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.NPC;

import script.Main;
import script.p;
import script.actionz.UniqueActions;
import script.actionz.UniqueActions.Actionz;
import script.behaviour.DecisionLeaf;
import script.framework.Leaf;
import script.framework.Tree;
import script.quest.varrockmuseum.Timing;
import script.utilities.API;
import script.utilities.Dialoguez;
import script.utilities.InvEquip;
import script.utilities.Locations;
import script.utilities.Questz;
import script.utilities.Sleepz;
import script.utilities.Walkz;
import script.utilities.id;

import java.util.LinkedHashMap;
import java.util.List;
/**
 * Completes Restless Ghost
 * @author Dreambotter420
 * ^_^
 */
public class RestlessGhost extends Leaf {
	public static final int GHOSTSPEAK_AMULET = 552;
	public static final int GHOST_SKULL = 553;
	@Override
	public boolean isValid() {
		return API.mode == API.modes.RESTLESS_GHOST;
	}
	public static boolean completed()
	{
		if(getProgressValue() == 5)
		{
			if(Dialogues.getNPCDialogue() != null && 
	     			Dialogues.getNPCDialogue().contains("Release! Thank you stranger.."))
	     	{
	     		Sleepz.sleep(696,696);
	     		API.randomAFK(50);
	     		return false;
	     	}
	     	if(Widgets.getWidgetChild(153,16) != null &&
	     			Widgets.getWidgetChild(153,16).isVisible())
	     	{
	     		if(Widgets.getWidgetChild(153,16).interact("Close")) Sleepz.sleep(696, 666);
	     		return false;
	     	}
	     	return true;
		}
		return false;
	}
    @Override
    public int onLoop() {
        if (DecisionLeaf.taskTimer.finished()) {
            Logger.log("[TIMEOUT] -> The Restless Ghost");
           	API.mode = null;
            return Timing.sleepLogNormalSleep();
        }
        if (completed()) {
            Logger.log("[COMPLETED] -> The Restless Ghost!");
           	API.mode = null;
           	Main.customPaintText1 = "~~~~~~~~~~";
    		Main.customPaintText2 = "~Quest Complete~";
    		Main.customPaintText3 = "~The Restless Ghost~";
    		Main.customPaintText4 = "~~~~~~~~~~";
            return Timing.sleepLogNormalSleep();
        }

    	if(Questz.shouldCheckQuestStep()) Questz.checkQuestStep("The Restless Ghost");
        if(Dialogues.getNPCDialogue() != null && !Dialogues.getNPCDialogue().isEmpty())
    	{
    		Logger.log("NPC Dialogue: " + Dialogues.getNPCDialogue());
    	}
        if(Dialoguez.handleDialogues()) return Timing.sleepLogNormalInteraction();
       
        
        switch(getProgressValue())
        {
       
        case(4):
        {
        	walkSearchCoffin();
    		return Timing.sleepLogNormalInteraction();
        }
        case(3):
        {
        	if(haveSkull())
        	{
        		walkSearchCoffin();
        		return Timing.sleepLogNormalInteraction();
        	}
        	getSkull();
    		return Timing.sleepLogNormalInteraction();
        }
        case(2):
        {
        	 if((!Equipment.contains(ghostspeakAmulet) && !InvEquip.equipmentContains(InvEquip.wearablePassages)) || 
         			!InvEquip.equipmentContains(InvEquip.wearableWealth) || 
         			!Inventory.contains(id.lumbridgeTele))
         	{
         		fulfillStart();
         		return Timing.sleepLogNormalInteraction();
         	}
        	if(Equipment.contains(ghostspeakAmulet))
        	{
        		if(Locations.restlessGhostUrhneyHut.contains(p.l) ||
        				Locations.restlessGhostSkeletonCoffin.getCenter().distance() > 125)
        		{
        			Walkz.teleportLumbridge(30000);
            		return Timing.sleepLogNormalSleep();
        		}
        		NPC ghost = NPCs.closest(n -> 
        		n!=null && 
        		n.getName().equals("Restless ghost") && 
        		n.hasAction("Talk-to"));
        		if(ghost == null)
        		{
        			Condition ghostAppear = () -> NPCs.closest(n -> 
            			n!=null && 
            			n.getName().equals("Restless ghost") && 
            			n.hasAction("Talk-to")) != null;
            		API.walkInteractWithGameObject("Coffin","Search", Locations.restlessGhostSkeletonCoffin,ghostAppear);
        			API.walkInteractWithGameObject("Coffin","Open", Locations.restlessGhostSkeletonCoffin,ghostAppear);
            		return Timing.sleepLogNormalSleep();
        		}
        		if(ghost.interact("Talk-to"))
        		{
        			Sleep.sleepUntil(Dialogues::inDialogue,
        					() -> p.l.isMoving(),Sleepz.calculate(3333, 2222),66);
        		}
        		return Timing.sleepLogNormalSleep();
        	}
        	if(Inventory.contains(ghostspeakAmulet))
        	{
        		InvEquip.equipItem(ghostspeakAmulet);
        		return Timing.sleepLogNormalSleep();
        	}
        	if(Bank.contains(ghostspeakAmulet))
        	{
        		InvEquip.withdrawOne(ghostspeakAmulet, 180000);
        		return Timing.sleepLogNormalSleep();
        	}
        	getGhostspeakAmulet();
        	return Timing.sleepLogNormalSleep();
        }
        case(1):
        {
        	 if((!Equipment.contains(ghostspeakAmulet) && !InvEquip.equipmentContains(InvEquip.wearablePassages)) || 
         			!InvEquip.equipmentContains(InvEquip.wearableWealth) || 
         			!Inventory.contains(id.lumbridgeTele))
         	{
         		fulfillStart();
         		return Timing.sleepLogNormalInteraction();
         	}
        	getGhostspeakAmulet();
        	return Timing.sleepLogNormalSleep();
        }
        case(0):
        {
        	 if((!Equipment.contains(ghostspeakAmulet) && !InvEquip.equipmentContains(InvEquip.wearablePassages)) || 
         			!InvEquip.equipmentContains(InvEquip.wearableWealth) || 
         			!Inventory.contains(id.lumbridgeTele))
         	{
         		fulfillStart();
         		return Timing.sleepLogNormalInteraction();
         	}
        	if(Locations.restlessGhostLumbyChurch.getCenter().distance() <= 100)
        	{
        		API.walkTalkToNPC("Father Aereck","Talk-to",Locations.restlessGhostLumbyChurch);
        		return Timing.sleepLogNormalSleep();
        	}
        	if(Locations.restlessGhostLumbyChurch.getCenter().distance() > 100)
        	{
        		if(Walkz.teleportLumbridge(180000))
        		{
        			Sleepz.sleep(696, 420);
        		}
        		return Timing.sleepLogNormalInteraction();
        	}
        }
        default:break;
        }
        return Timing.sleepLogNormalSleep();
    }
    /**
     * Returns true if ghostpeak in invy or equipment. Withdraws from bank or gets more from father urhney
     */
    public static boolean getGhostspeakAmulet()
    {
    	if(Equipment.contains(ghostspeakAmulet) || Inventory.contains(ghostspeakAmulet)) return true;
    	if(Bank.contains(ghostspeakAmulet))
    	{
    		InvEquip.withdrawOne(ghostspeakAmulet, 180000);
    		return false;
    	}
    	if(Locations.restlessGhostUrhneyHut.getCenter().distance() <= 125)
    	{
    		if(InvEquip.free1InvySpace())
    		{
    			API.walkTalkToNPC("Father Urhney","Talk-to",Locations.restlessGhostUrhneyHut);
    		}
    		return false;
    	}
    	if(Locations.restlessGhostUrhneyHut.getCenter().distance() > 125)
    	{
    		if(Walkz.teleportLumbridge(180000))
    		{
    			Sleepz.sleep(696, 420);
    		}
    	}
    	return false;
    }
    public static void fulfillStart()
    {
    	InvEquip.clearAll();
    	InvEquip.setEquipItem(EquipmentSlot.RING, InvEquip.wealth);
    	InvEquip.setEquipItem(EquipmentSlot.AMULET, InvEquip.passage);
    	InvEquip.addInvyItem(id.stamina4, 1, 1, false, 5);

    	InvEquip.addInvyItem(ghostspeakAmulet, 1, 1, false, 0);
    	InvEquip.addInvyItem(id.lumbridgeTele, 2, (int) Calculations.nextGaussianRandom(7, 2), false, 10);
    	InvEquip.shuffleFulfillOrder();
    	InvEquip.addInvyItem(InvEquip.coins, 0, 0, false, 0);
    	if(InvEquip.fulfillSetup(true, 180000))
		{
			Logger.log("[INVEQUIP] -> Fulfilled equipment correctly! (restless ghost)");
			return;
		} else 
		{
			Logger.log("[INVEQUIP] -> NOT Fulfilled equipment correctly! (restless ghost)");
			return;
		}
    }
    public static void walkSearchCoffin()
    {
    	if(Locations.wizardTowerBasement.contains(p.l))
    	{
			Walkz.teleportLumbridge(180000);
			return;
    	}
		if(Locations.restlessGhostSkeletonCoffin.getCenter().distance() < 100)
		{
			Condition release = () -> Dialogues.getNPCDialogue() != null && Dialogues.getNPCDialogue().contains("Release! Thank you stranger..");
			API.walkInteractWithGameObject("Coffin","Open", Locations.restlessGhostSkeletonCoffin,release);
			API.walkInteractWithGameObject("Coffin","Search", Locations.restlessGhostSkeletonCoffin,release);
		}
    }
    public static boolean haveSkull()
    {
    	return PlayerSettings.getBitValue(2130) == 1;
    }
    public static void getSkull()
    {
    	if((!Equipment.contains(ghostspeakAmulet) && !InvEquip.equipmentContains(InvEquip.wearablePassages)) || 
     			!InvEquip.equipmentContains(InvEquip.wearableWealth) || 
     			!Inventory.contains(id.lumbridgeTele))
     	{
     		fulfillStart();
     		return;
     	}
    	if(Locations.wizardTowerBasement.contains(p.l))
    	{
    		API.walkInteractWithGameObject("Altar", "Search", Locations.wizardTowerBasementAltar,
    				() -> haveSkull());
    		return;
    	}
    	if(Locations.wizardTowerGroundFloorLadder.getCenter().distance() > 100)
    	{
    		Walkz.useJewelry(InvEquip.passage, "Wizards\' Tower");
    		return;
    	}
    	API.walkInteractWithGameObject("Ladder", "Climb-down", Locations.wizardTowerGroundFloorLadder,
    			() -> Locations.wizardTowerBasement.contains(p.l));
    }
    public static int getProgressValue()
    {
    	return PlayerSettings.getConfig(107);
    }
}
