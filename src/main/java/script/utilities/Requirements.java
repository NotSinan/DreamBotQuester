package script.utilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;

import java.util.Map;

public class Requirements {
    private int questPoints;
    private Map<String, Integer> skills;
    private Map<String, Integer> items;

    public Requirements(int questPoints, Map<String, Integer> skills, Map<String, Integer> items) {
        this.questPoints = questPoints;
        this.skills = skills;
        this.items = items;
    }

    public int getQuestPoints() {
        return questPoints;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean hasRequiredSkills() {
        for (Map.Entry<String, Integer> entry : this.skills.entrySet()) {
            String skill = entry.getKey();
            int level = entry.getValue();
            if (Skills.getRealLevel(Skill.valueOf(skill)) < level) {
                System.out.println("You do not have the required level in " + skill + " to complete this quest.");
                return false;
            }
        }
        return true;
    }

    public boolean hasRequiredQuestPoints() {
        if (Quests.getQuestPoints() < this.questPoints) {
            return false;
        }
        return true;
    }

    public boolean hasRequiredItems() {
        for (Map.Entry<String, Integer> entry : this.items.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            if (Inventory.count(item) < quantity) {
                System.out.println("You do not have the required quantity of " + item + " to complete this quest.");
                return false;
            }
        }
        return true;
    }

    public boolean checkRequirements() {
        if (hasRequiredItems() == true && hasRequiredSkills() == true && hasRequiredQuestPoints() == true) {
            return true;
        }
        return false;
    }
}
