package org.dreambot.quests.piratestreasure.smugglerum;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class LeaveKaramjaLeaf extends Leaf {
    private final Area SEAMAN_AREA = new Area(
            new Tile(2949, 3146, 0),
            new Tile(2961, 3146, 0),
            new Tile(2961, 3147, 0),
            new Tile(2956, 3148, 0),
            new Tile(2955, 3155, 0),
            new Tile(2954, 3159, 0),
            new Tile(2953, 3159, 0),
            new Tile(2953, 3148, 0),
            new Tile(2949, 3148, 0));
    private final String SEAMAN_NAME = "Customs officer";
    private final String[] DIALOGUE_OPTIONS = {"Can I journey on this ship?", "Search away, I have nothing to hide.", "Ok."};

    @Override
    public boolean isValid() { return SmuggleState.isOnKaramja() && SmuggleState.haveShippedRum(); }

    @Override
    public int onLoop() { return QuestHelper.goAndTalkToNpc(SEAMAN_AREA, SEAMAN_NAME, DIALOGUE_OPTIONS); }
}
