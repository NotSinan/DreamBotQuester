package org.dreambot.quests.vampyreslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveHammerLeaf extends Leaf {

    private final Area VARROCK_GENERAL_STORE = new Area(3214, 3418, 3220, 3411);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_VAMPYRE_SLAYER.getId()) == 1 &&
                Inventory.contains("Coins") &&
                Inventory.contains("Garlic") &&
                Inventory.contains("Beer") &&
                !Inventory.contains("Hammer");
    }

    @Override
    public int onLoop() {

        if (!VARROCK_GENERAL_STORE.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(VARROCK_GENERAL_STORE.getRandomTile());
            }
        } else {
            if (Shop.isOpen()) {
                Shop.purchase("Hammer", 1);
                Sleep.sleepUntil(() -> Inventory.contains("Hammer"), 3000);
            } else {
                NPC shopAssistant = NPCs.closest("Shop assistant");
                if (shopAssistant.interact("Trade")) {
                    Sleep.sleepUntil(() -> Shop.isOpen(), 3000);
                }
            }
        }
        return Timing.loopReturn();
    }
}
