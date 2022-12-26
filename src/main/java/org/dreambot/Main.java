package org.dreambot;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.framework.Tree;
import org.dreambot.framework.fallback.FallbackLeaf;
import org.dreambot.framework.timeout.TimeoutLeaf;
import org.dreambot.paint.CustomPaint;
import org.dreambot.paint.PaintInfo;
import org.dreambot.quests.cooksassistant.CooksAssistant;
import org.dreambot.quests.cooksassistant.FinishedCooksAssistant;
import org.dreambot.quests.cooksassistant.GatherItemsLeaf;
import org.dreambot.quests.cooksassistant.TalkToCookLeaf;
import org.dreambot.quests.druidicritual.*;
import org.dreambot.quests.sheepshearer.CollectWoolLeaf;
import org.dreambot.quests.sheepshearer.SheepShearer;
import org.dreambot.quests.sheepshearer.SpinWoolLeaf;
import org.dreambot.quests.sheepshearer.TalkToFredLeaf;
import org.dreambot.quests.doricsquest.DoricsQuest;
import org.dreambot.quests.doricsquest.TalkToDoricLeaf;
import org.dreambot.quests.impcatcher.GiveBeadsLeaf;
import org.dreambot.quests.impcatcher.ImpCatcher;
import org.dreambot.quests.impcatcher.RetrieveBeadsLeaf;
import org.dreambot.quests.piratestreasure.*;
import org.dreambot.quests.piratestreasure.RetrieveCoinsLeaf;
import org.dreambot.quests.romeoandjuliet.*;
import org.dreambot.quests.runemysteries.*;
import org.dreambot.quests.therestlessghost.*;
import org.dreambot.quests.vampyreslayer.*;
import org.dreambot.quests.xmarksthespot.*;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

import java.awt.*;

@ScriptManifest(author = "Bonfire", name = "DreamBot TBL", version = 1.00, category = Category.MAGIC)
public class Main extends AbstractScript implements PaintInfo {

    // Instantiate the tree to hold our branches and leaves
    private final Tree tree = new Tree();

    // Instantiate the paint object. This can be customized to your liking.
    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.BOTTOM_LEFT_PLAY_SCREEN, new Color[]{new Color(255, 251, 255)},
            "Trebuchet MS",
            new Color[]{new Color(50, 50, 50, 175)},
            new Color[]{new Color(28, 28, 29)},
            1, false, 5, 3, 0);
    private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Our onStart that supports arguments
    @Override
    public void onStart(String... args) {
        instantiateTree();
    }

    // Our onStart for when no arguments have been passed to the script
    @Override
    public void onStart() {
        instantiateTree();
    }

    @Override
    public void onExit() {
        Timing.tickTimeout = 0;
        Timing.sleepLength = 0;
    }

    // Add all of the branches and leaves to the tree
    private void instantiateTree() {
        tree.addBranches(
                new TimeoutLeaf(),
//                // Place your own branches and leaves below this. The TimeoutLeaf waits one tick and decrements Timing.tickTimeout int.

                new DruidicRitual().addLeafs(new RetrieveRatMeatLeaf(), new RetrieveCowMeatLeaf(), new RetrieveChickenMeatLeaf(),
                        new RetrieveBearMeatLeaf(), new TalkToKaqemeexLeaf(), new TalkToSanfewLeaf(), new EnchantMeatLeaf()),
                //new SheepShearer().addLeafs(new TalkToFredLeaf(), new CollectWoolLeaf(), new SpinWoolLeaf())

//                // Place your own branches and leaves above this. The FallbackLeaf is a failsafe in case there none of the leafs execute, and generates new Timing.tickTimeout.
                new FallbackLeaf()
        );
    }

    // Infinite loop. Returns the current leaf and executes it
    @Override
    public int onLoop() {
        return this.tree.onLoop();
    }

    // Our paint info
    // Add new lines to the paint here
    @Override
    public String[] getPaintInfo() {
        return new String[]{
                getManifest().name() + " V" + getManifest().version(),
                "Current Branch: " + API.currentBranch,
                "Current Leaf: " + API.currentLeaf,
                "Tick Timeout: " + Timing.tickTimeout,
                "Sleep Delay: " + Timing.sleepLength + "ms"
        };
    }

    // onPaint (you probably don't need to touch this)
    @Override
    public void onPaint(Graphics2D graphics2D) {
        graphics2D.setRenderingHints(aa);
        CUSTOM_PAINT.paint(graphics2D);
    }
}
