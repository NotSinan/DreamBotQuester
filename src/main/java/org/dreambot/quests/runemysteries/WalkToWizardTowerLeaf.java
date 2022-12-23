package org.dreambot.quests.runemysteries;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class WalkToWizardTowerLeaf extends Leaf {

    private final Area WIZARD_TOWER_AREA = new Area(3099, 9574, 3107, 9569);
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 1 &&
                Inventory.contains("Air talisman") &&
                !WIZARD_TOWER_AREA.contains(Players.getLocal()) ||
                !WIZARD_TOWER_AREA.contains(Players.getLocal()) && PlayerSettings.getConfig(QuestVarPlayer.QUEST_RUNE_MYSTERIES.getId()) == 5 && Inventory.contains("Research notes");
    }

    @Override
    public int onLoop() {
        if (!WIZARD_TOWER_AREA.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(WIZARD_TOWER_AREA.getRandomTile());
            }
            return Timing.loopReturn();
        }
        return 400;
    }
}
