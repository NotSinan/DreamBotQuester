package script.quests.therestlessghost;

import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.utilities.Logger;
import script.utilities.Questz;

import java.util.List;
import java.util.Map;

public class TheRestlessGhost extends Questz
{

    public TheRestlessGhost(int questPoints, Map<Skill, Integer> skills, List<Quest> quests) {
        super(questPoints, skills, quests);
    }

    @Override
    public boolean isValid() {
        return !completed() && reqs.checkRequirements();
    }

    @Override
    public int onLoop() {
        return 0;
    }

    @Override
    public boolean completed() {
        return false;
    }

    @Override
    public int getProgressValue() {
        return PlayerSettings.getConfig(107);
    }

    public boolean handleDialogues() {
        if(Dialogues.areOptionsAvailable())
        {
            if(Dialogues.chooseOption("Yes.") ||
                    Dialogues.chooseOption("I'm looking for a quest!") ||
                    Dialogues.chooseOption("Yep, now tell me what the problem is.") ||
                    Dialogues.chooseOption("Father Aereck sent me to talk to you.") ||
                    Dialogues.chooseOption("He's got a ghost haunting his graveyard.") ||
                    Dialogues.chooseOption("I've lost the Amulet of Ghostspeak."))
            {

            }
        }
        return false;
    }

}