package org.dreambot;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.behaviour.combat.CombatBranch;
import org.dreambot.behaviour.combat.leaves.CombatLeaf;
import org.dreambot.behaviour.fallback.FallbackLeaf;
import org.dreambot.behaviour.timeout.TimeoutLeaf;
import org.dreambot.framework.Tree;
import org.dreambot.paint.CustomPaint;
import org.dreambot.paint.PaintInfo;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

import javax.swing.*;
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
                // Place your own branches and leaves below this

                new CombatBranch().addLeafs(new CombatLeaf()),

                // Place your own branches and leaves above this
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
