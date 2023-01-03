package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;


import org.dreambot.api.methods.quest.book.MiniQuest;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.requirements.CheckRequirements;

public class AlfredGrimhandsBarcrawl extends Branch {
    public static boolean finishedCard = false;
    public static boolean checkedCard = false;

    @Override
    public boolean isValid() {
        return CheckRequirements.checkRequirements(MiniQuest.ALFRED_GRIMHANDS_BARCRAWL);
    }
}
