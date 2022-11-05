package script;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.utilities.Logger;
import script.framework.Tree;
import script.paint.CustomPaint;
import script.paint.PaintInfo;
import script.utilities.API;

@ScriptManifest(name = "Example Framework Script", author = "Dreambotter420", version = 1.69, category = Category.MISC)
public class Main extends AbstractScript implements PaintInfo, ChatListener
{
	
    @Override
    public void onStart(String[] args)
    {
    	//do stuff here to handle any arguments of Quickstart parameters passed
    	
    	start();
    }
    @Override
    public void onStart()
    {
    	start();
    }
    public void start()
	{
    	instantiateTasks();
	}
	
    public static Tree tree = new Tree();
    private void instantiateTasks()
    {
        // Tasks is actually just a branch and is executed in a row
    	tree.addBranches(
    			new WaitForLogged_N_Loaded().addLeafs(
    					new Waiting()
    					),
    			new GoToGE(),
    			new InGE()
    			);
    	Logger.log("Tasks instantiated");
    }

    
    @Override
    public int onLoop()
    {
        return tree.onLoop();
    }

    // Our paint info
    // Add new lines to the paint here
    @Override
    public String[] getPaintInfo()
    {
    	return new String[] {
    			getManifest().name() +" "+ getManifest().version() + " by Dreambotter420 ^_^",
                "Branch: " + API.currentBranch,
                "Leaf: " + API.currentLeaf
        };
    }

    // Instantiate the paint object. This can be customized to your liking.
    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.BOTTOM_LEFT_PLAY_SCREEN,
            new Color[] {new Color(255, 251, 255)},
            "Trebuchet MS",
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
}
