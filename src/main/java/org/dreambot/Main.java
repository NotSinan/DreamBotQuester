package org.dreambot;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.framework.Tree;
import org.dreambot.framework.bank.BankOnceLeaf;
import org.dreambot.framework.fallback.FallbackLeaf;
import org.dreambot.framework.timeout.TimeoutLeaf;
import org.dreambot.paint.CustomPaint;
import org.dreambot.paint.PaintInfo;
import org.dreambot.quests.alfredgrimhandsbarcrawl.*;
import org.dreambot.quests.piratestreasure.*;
import org.dreambot.quests.piratestreasure.SeeGardenerPresentLeaf;
import org.dreambot.quests.piratestreasure.smugglerum.*;
import org.dreambot.quests.piratestreasure.smugglerum.TalkToLuthasLeaf;
import org.dreambot.quests.piratestreasure.smugglerum.chatmsg.SeeCrateSentLeaf;
import org.dreambot.quests.piratestreasure.smugglerum.chatmsg.SeeFullBananasCrateLeaf;
import org.dreambot.quests.piratestreasure.smugglerum.chatmsg.SeeRumNoBananasLeaf;
import org.dreambot.quests.piratestreasure.smugglerum.chatmsg.SeeStashedRumLeaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

import java.awt.*;

@ScriptManifest(author = "Bonfire", name = "DreamBot TBL", version = 1.00, category = Category.MAGIC)
public class Main extends AbstractScript implements PaintInfo, ChatListener {

    // Instantiate the tree to hold our branches and leaves
    private final Tree tree = new Tree();

    // Instantiate the paint object. This can be customized to your liking.
    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.BOTTOM_LEFT_PLAY_SCREEN, new Color[]{new Color(255, 251, 255)},
            "Trebuchet MS",
            new Color[]{new Color(50, 50, 50, 175)},
            new Color[]{new Color(28, 28, 29)},
            1, false, 5, 3, 0);
    private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Our onStart that supports arguments
    @Override
    public void onStart(String... args) {
        instantiateTree();
    }

    // Our onStart for when no arguments have been passed to the script
    @Override
    public void onStart() {
        instantiateTree();
    }

    @Override
    public void onExit() {
        Timing.tickTimeout = 0;
        Timing.sleepLength = 0;
    }

    // Add all of the branches and leaves to the tree
    private void instantiateTree() {
        tree.addBranches(
                new TimeoutLeaf(),

                new BankOnceLeaf(),

//                Place your own branches and leaves below this. The TimeoutLeaf waits one tick and decrements Timing.tickTimeout int.

                new AlfredGrimhandsBarcrawl().addLeafs(new FinishedAlfredGrimhandsBarcrawlLeaf(), new SeeAllFinishedMessage(), new SeeBartenderMessageLeaf(),
                        new CheckCardLeaf(), new TalkToBarbarianGuardsLeaf(), new TalkToGrimhandBartenderLeaf()),

//                new CooksAssistant().addLeafs(new FinishedCooksAssistantLeaf(), new RetrievePotOfFlourLeaf(), new RetrieveBucketOfMilkLeaf(),
//                new RetrieveEggLeaf(), new TalkToCookLeaf()),

//                new DoricsQuest().addLeafs(new FinishedDoricsQuestLeaf(), new GetOresLeaf(), new TalkToDoricLeaf()),

//                new DruidicRitual().addLeafs(new FinishedDruidicRitualLeaf(), new RetrieveRatMeatLeaf(), new RetrieveCowMeatLeaf(),
//                new RetrieveChickenMeatLeaf(), new RetrieveBearMeatLeaf(), new TalkToKaqemeexLeaf(), new TalkToSanfewLeaf(), new EnchantMeatLeaf()),

//                new ImpCatcher().addLeafs(new FinishedImpCatcherLeaf(), new RetrieveBeadsLeaf(), new GiveBeadsLeaf()),

/*                new PiratesTreasure().addLeafs(new FinishedPiratesTreasureLeaf(), new WithdrawFromBankPiratesTreasureLeaf(), new TalkToRedbeardFrankLeaf(),
                        new RetrieveSpadePiratesTreasureLeaf(), new RetrievePiratesTreasureCoinsLeaf(),
                        new SmuggleRumBranch().addLeafs(new PauseForCutsceneLeaf(), new SeeCrateSentLeaf(), new SeeFullBananasCrateLeaf(), new SeeRumNoBananasLeaf(), new SeeStashedRumLeaf(),
                                new RetrieveSmuggledRumLeaf(), new EnterStoreBackhouseLeaf(), new LeaveKaramjaLeaf(), new RetrieveWhiteApronLeaf(),
                                new TalkToLuthasLeaf(), new FillCrateWithBananasLeaf(), new StashRumLeaf(), new RetrieveRumLeaf(), new RetrieveBananaLeaf()),
                        new RetrievePiratesMessageLeaf(), new SeeGardenerPresentLeaf(), new DigGardenLeaf()),
*/

//                new RomeoAndJuliet().addLeafs(new FinishedRomeoAndJulietLeaf(), new RemoveWizardWebnodesLeaf(), new TalkToRomeoLeaf(), new TalkToJulietLeaf(),
//                        new TalkToApothecary(), new TalkToFatherLawrenceLeaf()),

//                new RuneMysteries().addLeafs(new FinishedRuneMysteriesLeaf(), new RemoveDraynorJailWebnodesLeaf(),
//                  new TalkToLumbridgeDukeLeaf(), new TalkToAuburyLeaf(), new TalkToArchmageLeaf()),

//                new TheRestlessGhost().addLeafs(new FinishedRestlessGhostLeaf(), new RemoveDraynorJailNodesLeaf(), new RetrieveGhostHeadLeaf(), new TalkToFatherAereckLeaf(),
//                        new TalkToFatherUrhneyLeaf(), new TalkToGhostLeaf(), new ReturnSkullToGhostLeaf()),

//                new SheepShearer().addLeafs(new FinishedSheepShearerLeaf(), new CollectWoolLeaf(), new SpinWoolLeaf(), new TalkToFredLeaf()),

//                new SheepShearer().addLeafs(new TalkToFredLeaf(), new CollectWoolLeaf(), new SpinWoolLeaf()),

//                new VampyreSlayer().addLeafs(new FinishedVampyreSlayerLeaf(), new RemoveDraynorJailNodesLeaf(), new RetrieveCoinsLeaf(), new RetrieveGarlicLeaf(),
//                        new RetrieveHammerLeaf(), new TalkToBartenderLeaf(), new TalkToDrHarlowLeaf(), new TalkToMorganLeaf(), new FightCountDraculaLeaf()),

//                new WitchsPotion().addLeafs(new FinishedWitchsPotionLeaf(),
//                        new RetrieveRatsTail(), new RetrieveBurntMeat(), new RetrieveEyeOfNewt(), new RetrieveOnion(),
//                        new TalkToWitchLeaf(), new DrinkFromCauldronLeaf()),

                //new XMarksTheSpot().addLeafs(new FinishedXMarksTheSpotLeaf(), new RetrieveSpadeLeaf(), new TalkToVeosLumbridgeLeaf(), new TalkToVeosSarimLeaf(),
                        //new ClueStepOneLeaf(), new ClueStepTwoLeaf(), new ClueStepThreeLeaf(), new ClueStepFourLeaf()),

//                new MonksFriend().addLeafs(new TalkToBrotherOmadLeaf(), new RetrieveChildsBlanketLeaf()),

//                Place your own branches and leaves above this. The FallbackLeaf is a failsafe in case there none of the leafs execute, and generates new Timing.tickTimeout.
                new FallbackLeaf()
        );
    }

    // Infinite loop. Returns the current leaf and executes it
    @Override
    public int onLoop() {
        return this.tree.onLoop();
    }

    // Our paint info
    // Add new lines to the paint here
    @Override
    public String[] getPaintInfo() {
        return new String[]{
                getManifest().name() + " V" + getManifest().version(),
                "Current Branch: " + API.currentBranch,
                "Current Leaf: " + API.currentLeaf,
                "Tick Timeout: " + Timing.tickTimeout,
                "Sleep Delay: " + Timing.sleepLength + "ms"
        };
    }

    // onPaint (you probably don't need to touch this)
    @Override
    public void onPaint(Graphics2D graphics2D) {
        graphics2D.setRenderingHints(aa);
        CUSTOM_PAINT.paint(graphics2D);
    }

    @Override
    public void onMessage(Message msg) {
        switch(msg.getType()) {
            case TIMEOUT_MESSAGE:
            case GAME:
            case ENGINE:
            case WELCOME:
            case FEEDBACK:
            case BROADCAST:
            case NPC_EXAMINE:
            case ITEM_EXAMINE:
            case OBJECT_EXAMINE:
            {
                API.lastGameMessage = msg.getMessage();
            }
            break;
        }
    }
}
