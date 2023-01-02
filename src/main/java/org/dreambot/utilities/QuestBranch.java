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
import org.dreambot.quests.freequests.thecorsaircurse.*;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.*;
import org.dreambot.quests.freequests.therestlessghost.*;
import org.dreambot.quests.freequests.vampyreslayer.*;
import org.dreambot.quests.freequests.witchspotion.*;
import org.dreambot.quests.freequests.xmarksthespot.*;
import org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl.*;
import org.dreambot.quests.paidquests.druidicritual.*;
import org.dreambot.quests.paidquests.monksfriend.*;

import java.util.function.Supplier;

public enum QuestBranch {
    ROMEO_AND_JULIET(() -> {
        Branch questBranch = new RomeoAndJuliet();
        questBranch.addLeafs(
                new TalkToRomeoLeaf(),
                new TalkToJulietLeaf(),
                new TalkToFatherLawrenceLeaf(),
                new TalkToApothecary()
        );
        return questBranch;
    }),

    ALFRED_GRIMHANDS_BARCRAWL(() -> {
        Branch questBranch = new AlfredGrimhandsBarcrawl();
        questBranch.addLeafs(
                new SeeAllFinishedMessage(),
                new SeeBartenderMessageLeaf(),
                new CheckCardLeaf(),
                new TalkToBarbarianGuardsLeaf(),
                new TalkToGrimhandBartenderLeaf()
        );
        return questBranch;
    }),

    COOKS_ASSISTANT(() -> {
        Branch questBranch = new CooksAssistant();
        questBranch.addLeafs(
                new TalkToCookLeaf(),
                new RetrieveEggLeaf(),
                new RetrieveBucketOfMilkLeaf(),
                new RetrievePotOfFlourLeaf()
        );
        return questBranch;
    }),

    CORSAIR_CURSE(() -> {
        Branch questBranch = new TheCorsairCurse();
        questBranch.addLeafs(
                new PauseForCorsairCurseCutsceneLeaf(),
                new RetrieveTinderboxCorsairCurseLeaf(),
                new TalkToTockFarmLeaf(),
                new TalkToTockRimmingtonLeaf(),
                new TravelToCorsairCoveLeaf(),
                new SolveCurseBranch().addLeafs(
                        new TalkToIthoiLeaf(),
                        new TalkToArsenLeaf(),
                        new TalkToColinLeaf(),
                        new TalkToGnocciLeaf(),
                        new TalkToTessLeaf(),
                        new RetrieveSpadeCorsairCurseLeaf(),
                        new TalkToTockCorsair1Leaf(),
                        new DigForDollLeaf(),
                        new ObserveIthoiTelescopeLeaf()
                ),
                new TalkToTockCorsair2Leaf(),
                new SolveGnocciFoodMysteryLeaf(),
                new SolveArsenFoodMystery(),
                new SolveIthoiFoodMysteryLeaf(),
                new BurnIthoiLeaf(),
                new TalkToTockCorsair3Leaf(),
                new KillIthoiLeaf(),
                new TalkToTockCorsair4Leaf(),
                new RetrieveTinderboxCorsairCurseLeaf()
        );
        return questBranch;
    }),

    DRUIDIC_RITUAL(() -> {
        Branch questBranch = new DruidicRitual();
        questBranch.addLeafs(
                new FinishedDruidicRitualLeaf(),
                new RetrieveRatMeatLeaf(),
                new RetrieveCowMeatLeaf(),
                new RetrieveChickenMeatLeaf(),
                new RetrieveBearMeatLeaf(),
                new TalkToKaqemeexLeaf(),
                new TalkToSanfewLeaf(),
                new EnchantMeatLeaf()
        );
        return questBranch;
    }),

    DORICS_QUEST(() -> {
        Branch questBranch = new DoricsQuest();
        questBranch.addLeafs(
                new GetOresLeaf(),
                new TalkToDoricLeaf()
        );
        return questBranch;
    }),

    MONKS_FRIEND(() -> {
        Branch questBranch = new MonksFriend();
        questBranch.addLeafs(
                new TalkToBrotherOmadLeaf(),
                new RetrieveChildsBlanketLeaf()
        );
        return questBranch;
    }),

    IMP_CATCHER(() -> {
        Branch questBranch = new ImpCatcher();
        questBranch.addLeafs(
                new RetrieveBeadsLeaf(),
                new GiveBeadsLeaf()
        );
        return questBranch;
    }),


    PIRATES_TREASURE(() -> {
        Branch questBranch = new PiratesTreasure();
        questBranch.addLeafs(
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
                        new RetrieveBananaLeaf()
                ),
                new RetrievePiratesMessageLeaf(),
                new SeeGardenerPresentLeaf(),
                new DigGardenLeaf()
        );
        return questBranch;
    }),

    RESTLESS_GHOST(() -> {
        Branch questBranch = new TheRestlessGhost();
        questBranch.addLeafs(
                new RemoveDraynorJailNodesLeaf(),
                new RetrieveGhostHeadLeaf(),
                new TalkToFatherAereckLeaf(),
                new TalkToFatherUrhneyLeaf(),
                new TalkToGhostLeaf(),
                new ReturnSkullToGhostLeaf()
        );
        return questBranch;
    }),
    RUNE_MYSTERIES(() -> {
        Branch questBranch = new RuneMysteries();
        questBranch.addLeafs(
                new RemoveDraynorJailNodesLeaf(),
                new TalkToLumbridgeDukeLeaf(),
                new TalkToAuburyLeaf(),
                new TalkToArchmageLeaf()
        );
        return questBranch;
    }),

    VAMPYRE_SLAYER(() -> {
        Branch questBranch = new VampyreSlayer();
        questBranch.addLeafs(
                new RemoveDraynorJailNodesLeaf(),
                new RetrieveCoinsLeaf(),
                new RetrieveGarlicLeaf(),
                new RetrieveHammerLeaf(),
                new TalkToBartenderLeaf(),
                new TalkToDrHarlowLeaf(),
                new TalkToMorganLeaf(),
                new FightCountDraculaLeaf()
        );
        return questBranch;
    }),
    SHEEP_SHEARER(() -> {
        Branch questBranch = new SheepShearer();
        questBranch.addLeafs(
                new CollectWoolLeaf(),
                new SpinWoolLeaf(),
                new TalkToFredLeaf()
        );
        return questBranch;
    }),

    X_MARKS_THE_SPOT(() -> {
        Branch questBranch = new XMarksTheSpot();
        questBranch.addLeafs(new RetrieveSpadeXMarksTheSpotLeaf(),
                new TalkToVeosLumbridgeLeaf(),
                new TalkToVeosSarimLeaf(),
                new ClueStepOneLeaf(),
                new ClueStepTwoLeaf(),
                new ClueStepThreeLeaf(),
                new ClueStepFourLeaf()
        );
        return questBranch;
    }),

    WITCHS_POTION(() -> {
        Branch questBranch = new WitchsPotion();
        questBranch.addLeafs(new RetrieveSpadeXMarksTheSpotLeaf(),
                new EnableRunDuringCombatLeaf(),
                new TalkToWitchLeaf(),
                new RetrieveRatsTail(),
                new RetrieveOnion(),
                new RetrieveEyeOfNewt(),
                new RetrieveBurntMeat(),
                new DrinkFromCauldronLeaf()
        );
        return questBranch;
    });

    private final Supplier<Branch> questBranchSupplier;

    QuestBranch(Supplier<Branch> questBranchSupplier) {
        this.questBranchSupplier = questBranchSupplier;
    }

    public Branch getQuestBranch() {
        return questBranchSupplier.get();
    }
}

