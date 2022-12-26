package org.dreambot.quests.doricsquest;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.utilities.Logger;
import org.dreambot.framework.Branch;
import org.dreambot.utilities.Timing;

public class DoricsQuest extends Branch {

    @Override
    public boolean isValid() {
        return FreeQuest.DORICS_QUEST.hasRequirements() && Skills.getRealLevel(Skill.MINING) >= 15;
    }

}
