package org.dreambot.utilities;

import org.dreambot.framework.Branch;
import org.dreambot.quests.freequests.cooksassistant.*;
import org.dreambot.quests.freequests.romeoandjuliet.*;
import org.dreambot.quests.freequests.witchspotion.*;

public enum QuestBranch {
    WITCHS_POTION(
            new WitchsPotion().addLeafs(
                    new TalkToWitchLeaf(),
                    new RetrieveRatsTail(),
                    new RetrieveOnion(),
                    new RetrieveEyeOfNewt(),
                    new RetrieveBurntMeat(),
                    new DrinkFromCauldronLeaf(),
                    new FinishedWitchsPotionLeaf()
            )),

    COOKS_ASSISTANT(
            new CooksAssistant().addLeafs(
                    new TalkToCookLeaf(),
                    new RetrieveEggLeaf(),
                    new RetrieveBucketOfMilkLeaf(),
                    new RetrievePotOfFlourLeaf(),
                    new FinishedCooksAssistantLeaf()
            )),

    ROMEO_AND_JULIET(
            new RomeoAndJuliet().addLeafs(
                    new TalkToRomeoLeaf(),
                    new TalkToJulietLeaf(),
                    new TalkToFatherLawrenceLeaf(),
                    new TalkToApothecary(),
                    new FinishedRomeoAndJulietLeaf()
            ));

    private final Branch questBranch;

    QuestBranch(Branch questBranch) {
        this.questBranch = questBranch;
    }

    public Branch getQuestBranch() {
        return questBranch;
    }
}

