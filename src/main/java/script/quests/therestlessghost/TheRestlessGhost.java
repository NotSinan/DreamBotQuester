import jdk.jfr.internal.Logger;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.settings.PlayerSettings;
import script.utilities.Questz;

public class TheRestlessGhost() extends Questz
{

    @Override
    public boolean isValid() {
        return !completed() && reqs.checkRequirements();
    }

    @Override
    public boolean completed() {
        return false;
    }

    @Override
    public int getProgressValue() {
        return PlayerSettings.getConfig(107);
    }

    @Override
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
                Logger.log("");
            }
        }
    }

    @Override
    public int doLoop() {
        return 0;
    }
}