package script;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.api.wrappers.widgets.message.MessageType;
import script.framework.Tree;
import script.paint.CustomPaint;
import script.paint.PaintInfo;
import script.quests.cooksassistant.CooksAssistant;
import script.utilities.API;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

@ScriptManifest(
        category = Category.QUEST,
        name = "DreamBotQuester",
        description = "Does quests for DreamBot",
        author = "Sinan + Dreambotter420",
        version = 0.01
)
public class Main extends AbstractScript implements PaintInfo, ChatListener{

    private final Tree tree = new Tree();
    private void instantiateTree()
    {

        Map<Skill,Integer> SKILLS_COOKS_ASSISTANT = new LinkedHashMap<>();
        SKILLS_COOKS_ASSISTANT.put(Skill.STRENGTH,1);
        SKILLS_COOKS_ASSISTANT.put(Skill.COOKING,1);
        tree.addBranches(
                new CooksAssistant(0, SKILLS_COOKS_ASSISTANT,null));
    }
    @Override
    public void onStart()
    {
        instantiateTree();
    }
    @Override
    public int onLoop() {
        if(Client.getGameState() != GameState.LOGGED_IN) return Calculations.random(666,1222);

        return tree.onLoop();
    }
    @Override
    public String[] getPaintInfo()
    {

        return new String[] {
                getManifest().name() +" "+ getManifest().version() + " by Dreambotter420 ^_^",
                "Current branch: " + API.currentBranch,
                "Current leaf: " + API.currentLeaf
        };
    }

    // Instantiate the paint object. This can be customized to your liking.
    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.BOTTOM_LEFT_PLAY_SCREEN,
            new Color[] {new Color(255, 251, 255)},
            "Impact",
            new Color[] {new Color(50, 50, 50, 175)},
            new Color[] {new Color(28, 28, 29)},
            1, false, 5, 3, 0);
    private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


    @Override
    public void onPaint(Graphics2D graphics2D)
    {
        // Set the rendering hints
        graphics2D.setRenderingHints(aa);
        // Draw the custom paint
        CUSTOM_PAINT.paint(graphics2D);
    }

    @Override
    public void onMessage(Message msg)
    {
        if(msg.getType() == MessageType.GAME ||
                msg.getType() == MessageType.ENGINE ||
                msg.getType() == MessageType.BROADCAST ||
                msg.getType() == MessageType.FEEDBACK ||
                msg.getType() == MessageType.TIMEOUT_MESSAGE ||
                msg.getType() == MessageType.WELCOME)
        {
            API.lastGameMsg = msg.getMessage();
        }
    }
}
