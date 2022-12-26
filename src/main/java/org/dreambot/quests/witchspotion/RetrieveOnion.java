package org.dreambot.quests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.widget.helpers.ItemProcessing;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveOnion extends Leaf {
    private final Area ONION_AREA = new Area(2955, 3254, 2945, 3248, 0);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_WITCHS_POTION.getId()) == 1 && !Inventory.contains("Onion");
    }

    @Override
    public int onLoop() {
        if (QuestHelper.walkToArea(ONION_AREA)) {
            GameObject onion = GameObjects.closest("Onion");
            if (onion != null && onion.exists() && Interaction.delayEntityInteract(onion, "Pick")) {
                Sleep.sleepUntil(() -> Inventory.contains("Onion"),
                        () -> Players.getLocal().isMoving() || Players.getLocal().isAnimating(),
                        3000, 100);
            }
        }
        return Timing.loopReturn();
    }
}
