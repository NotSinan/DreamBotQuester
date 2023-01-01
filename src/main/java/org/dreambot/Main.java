package org.dreambot;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.framework.Tree;
import org.dreambot.framework.bank.BankOnceLeaf;
import org.dreambot.framework.fallback.FallbackLeaf;
import org.dreambot.framework.timeout.TimeoutLeaf;
import org.dreambot.paint.CustomPaint;
import org.dreambot.paint.PaintInfo;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.ui.UserInterface;

import javax.swing.*;
import java.awt.*;

@ScriptManifest(author = "Sinan x 420", name = "DreamBotQuester", version = 1.0, category = Category.QUEST)
public class Main extends AbstractScript implements PaintInfo, ChatListener {

    private final Tree tree = new Tree();

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

    @Override
    public void onStart() {
        SwingUtilities.invokeLater(() -> {
            UserInterface ui = new UserInterface();
        });
    }

    @Override
    public void onExit() {
        Timing.tickTimeout = 0;
        Timing.sleepLength = 0;
    }

    private void instantiateTree() {
        tree.addBranches(
                new TimeoutLeaf(),
                new BankOnceLeaf(),
                UserInterface.getSelectedItem().getQuestBranch(),
                new FallbackLeaf()
        );
    }

    @Override
    public int onLoop() {
        if (UserInterface.isStartLoop()) {
            instantiateTree();
        }
        return this.tree.onLoop();
    }

    @Override
    public String[] getPaintInfo() {
        return new String[]{
                getManifest().name() + " V" + getManifest().version(),
                "Current Branch: " + API.currentBranch,
                "Current Leaf: " + API.currentLeaf,
                "Tick Timeout: " + Timing.tickTimeout,
                "Sleep Delay: " + Timing.sleepLength + "ms",
                "Quest: " + UserInterface.getSelectedItem()
        };
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        graphics2D.setRenderingHints(aa);
        CUSTOM_PAINT.paint(graphics2D);
    }

    @Override
    public void onMessage(Message msg) {
        switch(msg.getType()) {
            case TIMEOUT_MESSAGE:
            case GAME:
            case ENGINE:
            case WELCOME:
            case FEEDBACK:
            case BROADCAST:
            case NPC_EXAMINE:
            case ITEM_EXAMINE:
            case OBJECT_EXAMINE:
            {
                API.lastGameMessage = msg.getMessage();
            }
            break;
        }
    }
}
