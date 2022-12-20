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
    public abstract boolean handleDialogues();
    public abstract int doLoop();
    public Requirements reqs;
    public Questz(int questPoints, Map<Skill, Integer> skills, List<Quest> quests) {
        this.reqs = new Requirements(questPoints,skills,quests);
    }

    @Override
    public int onLoop()
    {
        if(closeQuestCompletion()) return Calculations.random(420,1111);
        if(Dialogues.inDialogue() && handleDialogues()) return Calculations.random(420,1111);
        return doLoop();
    }
    private boolean closeQuestCompletion()
    {
        WidgetChild close = Widgets.getWidgetChild(153,17);
        if(close != null && close.isVisible())
        {
            if(close.interact("Close")) Sleep.sleepTick();
            return true;
        }
        return false;
    }
}
