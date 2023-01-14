package org.dreambot.utilities.requirements;

import org.dreambot.api.data.requirements.Requirement;
import org.dreambot.api.methods.container.impl.Inventory;

public class ItemRequirement extends Requirement {
  @Override
  public boolean meetsRequirement() {
    return Inventory.count(this.itemName) >= this.itemQuantity;
  }

  private String itemName;
  private int itemQuantity;

  public ItemRequirement(String itemName, int itemQuantity) {
    this.itemName = itemName;
    this.itemQuantity = itemQuantity;
  }

  public int getItemQuantity() {
    return this.itemQuantity;
  }

  public String getItemName() {
    return this.itemName;
  }
}
