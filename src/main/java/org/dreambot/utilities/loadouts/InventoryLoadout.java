package org.dreambot.utilities.loadouts;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankMode;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.utilities.OwnedItems;
import org.dreambot.utilities.Timing;

public class InventoryLoadout {
  private List<LoadoutItem> items;

  public InventoryLoadout(LoadoutItem... items) {
    this.items = new ArrayList<>(Arrays.asList(items));
  }

  public void addItem(LoadoutItem... loadoutItem) {
    items.addAll(Arrays.asList(loadoutItem));
  }

  public boolean fulfilled() {
    // check extra items
    List<LoadoutItem> extraEquipmentItems = getExtraItems();
    if (!extraEquipmentItems.isEmpty()) {
      return false;
    }

    // check missing items
    for (LoadoutItem item : items) {
      if (!OwnedItems.contains(item.getItemName())) continue;
      Item invyItem = Inventory.get(item.getItemName());

      if (invyItem == null
          || !invyItem.isValid()
          || invyItem.getID() == -1
          || invyItem.getName() == null
          || invyItem.getName().equalsIgnoreCase("null")) {
        return false;
      }

      if (invyItem.isNoted() != item.isNoted()) {
        return false;
      }

      int currentQuantity = item.getItemQty();
      int neededQuantity = item.getItemQty() - currentQuantity;
      if (neededQuantity > 0) {
        return false;
      }
    }

    return true;
  }

  public boolean fulfill() {
    // 90s to open nearest bank
    Instant end = Instant.now().plusSeconds(90);
    while (end.isAfter(Instant.now())
        && ScriptManager.getScriptManager().isRunning()
        && !ScriptManager.getScriptManager().isPaused()) {
      if (!Bank.isOpen()) {
        Timing.sleepForDelay();
        Bank.open();
        Timing.sleepForTickDelay();
        continue;
      }
      break;
    }

    // 90s timer to fulfill equipment while in bank
    end = Instant.now().plusSeconds(90);
    while (end.isAfter(Instant.now())
        && ScriptManager.getScriptManager().isRunning()
        && !ScriptManager.getScriptManager().isPaused()) {
      // diff check of LoadoutItems parameter and given Inventory before depositing all - fuck you
      // camal
      List<LoadoutItem> extraItems = getExtraItems();
      if (!extraItems.isEmpty()) {
        Timing.sleepForDelay();
        if (Bank.depositAllItems()) {
          extraItems.stream()
              .forEach(
                  i ->
                      Logger.log(
                          "found extra item: "
                              + i.getItemName()
                              + " in qty: "
                              + i.getItemQty()
                              + ", noted: "
                              + i.isNoted()));
          Logger.log("Depositing inventory ^^^");
          Timing.sleepForTickDelay();
        }
        continue;
      }
      boolean neededSomething = false;
      for (LoadoutItem item : items) {
        int currentQuantity = Inventory.count(item.getItemName());
        int neededQuantity = item.getItemQty() - currentQuantity;
        if (neededQuantity > 0) {
          // Withdraw needed quantity from bank
          if (Bank.count(item.getItemName()) <= 0) {
            continue;
          }
          Timing.sleepForDelay();
          neededSomething = true;

          // Set noted mode
          BankMode withdrawMode = (item.isNoted() ? BankMode.NOTE : BankMode.ITEM);
          if (Bank.getWithdrawMode() != withdrawMode) {
            Bank.setWithdrawMode(withdrawMode);
            break;
          }

          if (Bank.withdraw(item.getItemName(), neededQuantity)) {
            Sleep.sleepUntil(
                () -> Inventory.count(item.getItemName()) == item.getItemQty(), 4000, 100);
          }
        }
      }
      if (!neededSomething) {
        Logger.log(
            "InventoryLoadout.fulfill() has nothing missing and nothing extra from loadout,"
                + " fulfilled inventory!");
        return true;
      }
    }
    Logger.log(
        "120s timeout for EquipmentLoadout.fulfill() occurred - failed to fulfill inventory");
    return false;
  }

  private List<LoadoutItem> getExtraItems() {
    List<LoadoutItem> extraItems = new ArrayList<>();
    List<LoadoutItem> inventoryItems = new ArrayList<>();

    for (Item i : Inventory.all()) {
      if (i == null || !i.isValid()) {
        continue;
      }
      inventoryItems.add(new LoadoutItem(i.getName(), i.getAmount(), i.isNoted()));
    }

    for (LoadoutItem item : items) {
      int extra = 0;
      for (LoadoutItem invyItem : inventoryItems) {
        if (invyItem.isNoted() == item.isNoted()
            && invyItem.getItemName().equals(item.getItemName())) {
          extra = invyItem.getItemQty();
          break;
        }
      }
      extra -= item.getItemQty();
      if (extra > 0) {
        extraItems.add(new LoadoutItem(item.getItemName(), extra));
      }
    }

    return extraItems;
  }
}
