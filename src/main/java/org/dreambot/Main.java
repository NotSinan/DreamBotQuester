package org.dreambot;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.framework.Tree;
import org.dreambot.framework.bank.BankOnceLeaf;
import org.dreambot.framework.fallback.FallbackLeaf;
import org.dreambot.framework.timeout.TimeoutLeaf;
import org.dreambot.framework.unbankables.UnbankableItemsLeaf;
import org.dreambot.paint.CustomPaint;
import org.dreambot.paint.PaintInfo;
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.ui.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@ScriptManifest(author = "Sinan x 420", name = "DreamBotQuester", version = 1.0, category = Category.QUEST)
public class Main extends AbstractScript implements PaintInfo, ChatListener {

    private final Tree tree = new Tree();
    private static UserInterface ui;

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
            ui = new UserInterface();
        });
    }

    @Override
    public void onExit() {
        ui.close();
        Timing.tickTimeout = 0;
        Timing.sleepLength = 0;
    }

    private void instantiateTree() {
        tree.addBranches(
                new TimeoutLeaf(),
                new UnbankableItemsLeaf(),
                new BankOnceLeaf(),
                API.selectedQuest.getQuestBranch(),
                new FallbackLeaf()
        );
    }

    @Override

    public int onLoop() {
        if (UserInterface.stopScript) {
            return -1;
        }

        if (UserInterface.startLoop) {
            instantiateTree();
        }
        return this.tree.onLoop();
    }

    @Override
    public String[] getPaintInfo() {
        List<String> paintInfo = new ArrayList<String>();
        paintInfo.add(getManifest().name() + " V" + getManifest().version());
        paintInfo.add("Current Branch: " + API.currentBranch);
        paintInfo.add("Current Leaf: " + API.currentLeaf);
        paintInfo.add("Active Timeout (Ticks): " + Timing.tickTimeout);
        paintInfo.add("Next Sleep Delay: " + Timing.sleepLength + "ms");
        paintInfo.add("Quest: " + API.selectedQuest);

        if (API.questVarPlayer > 0) {
            paintInfo.add("Quest VarPlayer (" + API.questVarPlayer + ") value: " + PlayerSettings.getConfig(API.questVarPlayer));
        }

        if (API.questVarBit > 0) {
            paintInfo.add("Quest VarBit (" + API.questVarBit + ") value: " + PlayerSettings.getBitValue(API.questVarBit));
        }

        return paintInfo.toArray(new String[0]);
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        graphics2D.setRenderingHints(aa);
        CUSTOM_PAINT.paint(graphics2D);
    }

    @Override
    public void onMessage(Message msg) {
        switch (msg.getType()) {
            case TIMEOUT_MESSAGE:
            case GAME:
            case ENGINE:
            case WELCOME:
            case FEEDBACK:
            case BROADCAST:
            case NPC_EXAMINE:
            case ITEM_EXAMINE:
            case OBJECT_EXAMINE: {
                API.lastGameMessage = msg.getMessage();
                API.lastGameMessageTile = Players.getLocal().getTile();
            }
            break;
        }
    }
}
