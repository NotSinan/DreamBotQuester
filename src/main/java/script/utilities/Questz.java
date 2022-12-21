package script.utilities;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import script.framework.Leaf;

import java.awt.*;
import java.util.List;
import java.util.Map;

public abstract class Questz extends Leaf
{
    public abstract boolean completed();
    public abstract int getProgressValue();
    public Requirements reqs;
    public Questz(int questPoints, Map<Skill, Integer> skills, List<Quest> quests) {
        this.reqs = new Requirements(questPoints,skills,quests);
    }
}
