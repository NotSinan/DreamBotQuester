package org.dreambot.utilities;

import org.dreambot.framework.Branch;
import org.dreambot.framework.webnodes.RemoveDraynorJailNodesLeaf;
import org.dreambot.quests.freequests.cooksassistant.*;
import org.dreambot.quests.freequests.doricsquest.*;
import org.dreambot.quests.freequests.impcatcher.*;
import org.dreambot.quests.freequests.piratestreasure.*;
import org.dreambot.quests.freequests.piratestreasure.smugglerum.*;
import org.dreambot.quests.freequests.piratestreasure.smugglerum.chatmsg.*;
import org.dreambot.quests.freequests.romeoandjuliet.*;
import org.dreambot.quests.freequests.runemysteries.*;
import org.dreambot.quests.freequests.sheepshearer.*;
import org.dreambot.quests.freequests.therestlessghost.*;
import org.dreambot.quests.freequests.vampyreslayer.*;
import org.dreambot.quests.freequests.witchspotion.*;
import org.dreambot.quests.freequests.xmarksthespot.*;
import org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl.*;
import org.dreambot.quests.paidquests.druidicritual.*;
import org.dreambot.quests.paidquests.monksfriend.*;


public enum QuestBranch {
    ALFRED_GRIMHANDS_BARCRAWL(
            new AlfredGrimhandsBarcrawl().addLeafs(
                    new SeeAllFinishedMessage(),
                    new SeeBartenderMessageLeaf(),
                    new CheckCardLeaf(),
                    new TalkToBarbarianGuardsLeaf(),
                    new TalkToGrimhandBartenderLeaf()
            )),

    COOKS_ASSISTANT(
            new CooksAssistant().addLeafs(
                    new TalkToCookLeaf(),
                    new RetrieveEggLeaf(),
                    new RetrieveBucketOfMilkLeaf(),
                    new RetrievePotOfFlourLeaf(),
                    new FinishedCooksAssistantLeaf()
            )),

    DORICS_QUEST(
            new DoricsQuest().addLeafs(
                    new FinishedDoricsQuestLeaf(),
                    new GetOresLeaf(),
                    new TalkToDoricLeaf()
            )),

    DRUIDIC_RITUAL(
            new DruidicRitual().addLeafs(
                    new FinishedDruidicRitualLeaf(),
                    new RetrieveRatMeatLeaf(),
                    new RetrieveCowMeatLeaf(),
                    new RetrieveChickenMeatLeaf(),
                    new RetrieveBearMeatLeaf(),
                    new TalkToKaqemeexLeaf(),
                    new TalkToSanfewLeaf(),
                    new EnchantMeatLeaf()
            )),

    IMP_CATCHER(
            new ImpCatcher().addLeafs(
                    new FinishedImpCatcherLeaf(),
                    new RetrieveBeadsLeaf(),
                    new GiveBeadsLeaf()
            )),

    MONKS_FRIEND(
            new MonksFriend().addLeafs(
                    new TalkToBrotherOmadLeaf(),
                    new RetrieveChildsBlanketLeaf()
            )),

    PIRATES_TREASURE(
            new PiratesTreasure().addLeafs(
                    new FinishedPiratesTreasureLeaf(),
                    new WithdrawFromBankPiratesTreasureLeaf(),
                    new TalkToRedbeardFrankLeaf(),
                    new RetrieveSpadePiratesTreasureLeaf(),
                    new RetrievePiratesTreasureCoinsLeaf(),
                    new SmuggleRumBranch().addLeafs(
                            new PauseForCutsceneLeaf(),
                            new SeeCrateSentLeaf(),
                            new SeeFullBananasCrateLeaf(),
                            new SeeRumNoBananasLeaf(),
                            new SeeStashedRumLeaf(),
                            new RetrieveSmuggledRumLeaf(),
                            new EnterStoreBackhouseLeaf(),
                            new LeaveKaramjaLeaf(),
                            new RetrieveWhiteApronLeaf(),
                            new TalkToLuthasLeaf(),
                            new FillCrateWithBananasLeaf(),
                            new StashRumLeaf(),
                            new RetrieveRumLeaf(),
                            new RetrieveBananaLeaf()),
                    new RetrievePiratesMessageLeaf(),
                    new SeeGardenerPresentLeaf(),
                    new DigGardenLeaf()
            )),

    RUNE_MYSTERIES(
            new RuneMysteries().addLeafs(
                    new FinishedRuneMysteriesLeaf(),
                    new RemoveDraynorJailNodesLeaf(),
                    new TalkToLumbridgeDukeLeaf(),
                    new TalkToAuburyLeaf(),
                    new TalkToArchmageLeaf()
            )),

    RESTLESS_GHOST(
            new TheRestlessGhost().addLeafs(
                    new RemoveDraynorJailNodesLeaf(),
                    new RetrieveGhostHeadLeaf(),
                    new TalkToFatherAereckLeaf(),
                    new TalkToFatherUrhneyLeaf(),
                    new TalkToGhostLeaf(),
                    new ReturnSkullToGhostLeaf()
            )),

    SHEEP_SHEARER(
            new SheepShearer().addLeafs(
                    new CollectWoolLeaf(),
                    new SpinWoolLeaf(),
                    new TalkToFredLeaf()
            )),

    VAMPYRE_SLAYER(
            new VampyreSlayer().addLeafs(
                    new RemoveDraynorJailNodesLeaf(),
                    new RetrieveCoinsLeaf(),
                    new RetrieveGarlicLeaf(),
                    new RetrieveHammerLeaf(),
                    new TalkToBartenderLeaf(),
                    new TalkToDrHarlowLeaf(),
                    new TalkToMorganLeaf(),
                    new FightCountDraculaLeaf()
            )),

    X_MARKS_THE_SPOT(
            new XMarksTheSpot().addLeafs(
                    new RetrieveSpadeXMarksTheSpotLeaf(),
                    new TalkToVeosLumbridgeLeaf(),
                    new TalkToVeosSarimLeaf(),
                    new ClueStepOneLeaf(),
                    new ClueStepTwoLeaf(),
                    new ClueStepThreeLeaf(),
                    new ClueStepFourLeaf()
            )),

    WITCHS_POTION(
            new WitchsPotion().addLeafs(
                    new EnableRunDuringCombatLeaf(),
                    new TalkToWitchLeaf(),
                    new RetrieveRatsTail(),
                    new RetrieveOnion(),
                    new RetrieveEyeOfNewt(),
                    new RetrieveBurntMeat(),
                    new DrinkFromCauldronLeaf()
            )),

    ROMEO_AND_JULIET(
            new RomeoAndJuliet().addLeafs(
                    new FinishedRomeoAndJulietLeaf(),
                    new TalkToRomeoLeaf(),
                    new TalkToJulietLeaf(),
                    new TalkToFatherLawrenceLeaf(),
                    new TalkToApothecary()
            ));

    private final Branch questBranch;

    QuestBranch(Branch questBranch) {
        this.questBranch = questBranch;
    }

    public Branch getQuestBranch() {
        return questBranch;
    }
}

