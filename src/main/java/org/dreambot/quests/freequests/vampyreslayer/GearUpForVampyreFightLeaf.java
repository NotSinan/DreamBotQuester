package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.loadouts.EquipmentLoadout;
import org.dreambot.utilities.loadouts.InventoryLoadout;
import org.dreambot.utilities.loadouts.LoadoutItem;
import org.dreambot.utilities.loadouts.OwnedItems;
import org.dreambot.utilities.loadouts.setups.melee.StandardMeleeLoadouts;

public class GearUpForVampyreFightLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(FreeQuest.VAMPIRE_SLAYER.getConfigID()) == 2 &&
                ((!Inventory.containsAll("Garlic", "Stake", "Hammer") && OwnedItems.contains()) || !Equipment.isSlotEmpty(EquipmentSlot.WEAPON));
    }

    @Override
    public int onLoop() {
        EquipmentLoadout bestLoadout = StandardMeleeLoadouts.getBestOwnedMeleeGear();
        if (!bestLoadout.fulfill()) {
            return Timing.loopReturn();
        }

        if (Equipment.isSlotEmpty(EquipmentSlot.WEAPON)) {
            if (OwnedItems.contains("Goblin paint cannon")) {
                if (Inventory.contains("Goblin paint cannon")) {
                    Interaction.delayInventoryInteract("Goblin paint cannon", "Wield");
                    return Timing.loopReturn();
                }

                bestLoadout.addItem(new LoadoutItem("Goblin paint cannon", 1));
                bestLoadout.fulfill();
                return Timing.loopReturn();
            }

            InventoryLoadout fightPlusCoins = new InventoryLoadout(
                    new LoadoutItem("Coins", Calculations.random(300,400)),
                    new LoadoutItem("Garlic", 1),
                    new LoadoutItem("Stake", 1),
                    new LoadoutItem("Hammer", 1)
            );
            if (fightPlusCoins.fulfill()) {
                Tile DIANGO_TILE = new Tile(3081,3249);
                return QuestHelper.purchaseFromShop(
                        DIANGO_TILE.getArea(10),
                        "Goblin paint cannon",
                        1,
                        "Diango"
                );
            }
        }
        return Timing.loopReturn();
    }
}
