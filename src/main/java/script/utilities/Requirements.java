package script.utilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.utilities.Logger;
import script.framework.Leaf;

import java.util.List;
import java.util.Map;

public class Requirements {
    private int questPoints;
    private Map<Skill, Integer> skills;
    private List<Quest> quests;

    public Requirements(int questPoints, Map<Skill, Integer> skills, List<Quest> quests) {
        this.questPoints = questPoints;
        this.quests = quests;
        this.skills = skills;
    }

    public int getQuestPoints() {
        return questPoints;
    }

    public Map<Skill, Integer> getSkills() {
        return skills;
    }


    public boolean hasRequiredQuests()
    {
        if(this.quests == null || this.quests.isEmpty()) return true;
        for(Quest q : this.quests)
        {
            if(!q.isFinished())
            {
                Logger.log("You do not have the required quest completed: " +q+ " to start this quest.");
                return false;
            }
        }
        return true;
    }
    public boolean hasRequiredSkills() {
        if(this.skills == null || this.skills.isEmpty()) return true;
        for (Map.Entry<Skill, Integer> entry : this.skills.entrySet()) {
            Skill skill = entry.getKey();
            int level = entry.getValue();
            if (Skills.getRealLevel(skill) < level) {
                Logger.log("You do not have the required level in " + skill + " to complete this quest.");
                return false;
            }
        }
        return true;
    }

    public boolean hasRequiredQuestPoints() {
        if (Quests.getQuestPoints() < this.questPoints) {
            Logger.log("You do not have the required QP:" + Quests.getQuestPoints() + " necessary for this quest:"+this.questPoints);
            return false;
        }
        return true;
    }

    public boolean checkRequirements() {
        return hasRequiredSkills() &&
                hasRequiredQuestPoints() &&
                hasRequiredQuests();
    }
}
