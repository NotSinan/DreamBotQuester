package org.dreambot.quests.gertrudescat;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindKittensLeaf extends Leaf {

    private final Area KITTEN_SEARCH_AREA = new Area(
            new Tile[] {
                    new Tile(3293, 3512, 0),
                    new Tile(3298, 3517, 0),
                    new Tile(3321, 3517, 0),
                    new Tile(3325, 3505, 0),
                    new Tile(3313, 3494, 0),
                    new Tile(3299, 3494, 0)
            }
    );

    Map<NPC, Boolean> crates = new HashMap<>();
    List<NPC> cratesList;

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_GERTRUDES_CAT.getId()) == 4 && !Inventory.contains("Fluffs' kitten");
    }

    @Override
    public int onLoop() {
        if (!KITTEN_SEARCH_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(KITTEN_SEARCH_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (cratesList == null) {
            cratesList = NPCs.all("Crate");
            for (NPC crate : cratesList) {
                crates.put(crate, false);
            }
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                Sleep.sleepUntil(() -> !Dialogues.inDialogue(), 3000);
            }
        }

        if (!Inventory.contains("Fluffs' kitten")) {
            for (Map.Entry<NPC, Boolean> entry : crates.entrySet()) {
                Logger.log(crates);
                NPC crate = entry.getKey();
                boolean interacted = entry.getValue();
                if (!interacted) {
                    crate.interact("Search");
                    if (Sleep.sleepUntil(() -> crate.distance() <= 1, 2400, 200)) {
                        Logger.log(crate.distance());
                        crates.put(crate, true);
                    }
                    return Timing.loopReturn();
                }
            }
        }
        return Timing.loopReturn();
    }
}