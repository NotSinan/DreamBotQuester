package org.dreambot.quests.freequests.vampyreslayer;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.OwnedItems;
import org.dreambot.utilities.Timing;
import org.dreambot.utilities.helpers.ShopHelper;
import org.dreambot.utilities.loadouts.EquipmentLoadout;
import org.dreambot.utilities.loadouts.InventoryLoadout;
import org.dreambot.utilities.loadouts.Loadout;
import org.dreambot.utilities.loadouts.LoadoutItem;
import org.dreambot.utilities.loadouts.setups.food.Food;
import org.dreambot.utilities.loadouts.setups.melee.StandardMeleeLoadouts;

public class GearUpForVampyreFightLeaf extends Leaf {

  @Override
  public boolean isValid() {
    return PlayerSettings.getConfig(FreeQuest.VAMPIRE_SLAYER.getConfigID()) == 2
        && Equipment.isSlotEmpty(EquipmentSlot.WEAPON);
  }

  public final int coinsQty = Calculations.random(300, 400);
  public final int foodQty = Calculations.random(10, 15);

  @Override
  public int onLoop() {
    EquipmentLoadout bestLoadout = StandardMeleeLoadouts.getBestOwnedMeleeGear();
    InventoryLoadout vampyreFightInventory = new InventoryLoadout();
    vampyreFightInventory.addItem(new LoadoutItem("Garlic", 1));
    vampyreFightInventory.addItem(new LoadoutItem("Stake", 1));
    vampyreFightInventory.addItem(new LoadoutItem("Hammer", 1));
    Food food = Food.getBestOwnedFood();
    if (food != null) {
      vampyreFightInventory.addItem(new LoadoutItem(food.foodName(), foodQty));
    }
    Loadout bestVampyreSetup = new Loadout(bestLoadout, vampyreFightInventory);
    if (!bestVampyreSetup.fulfilled()) {
      bestVampyreSetup.fulfill();
      return Timing.loopReturn();
    }

    // fulfilled and still have no weapon slot
    if (OwnedItems.contains("Goblin paint cannon")) {
      bestVampyreSetup.addEquipmentItem(new LoadoutItem("Goblin paint cannon", 1));
      bestVampyreSetup.fulfill();
      return Timing.loopReturn();
    }
    // add coins to loadout and go to diango
    bestVampyreSetup.addInventoryItem(new LoadoutItem("Coins", coinsQty));
    if (!bestVampyreSetup.fulfilled()) {
      bestVampyreSetup.fulfill();
      return Timing.loopReturn();
    }
    Tile DIANGO_TILE = new Tile(3081, 3249);
    return ShopHelper.purchaseFromShop(DIANGO_TILE.getArea(10), "Goblin paint cannon", 1, "Diango");
  }
}
