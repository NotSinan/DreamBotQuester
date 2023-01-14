package org.dreambot.utilities.loadouts;

public class LoadoutItem {
  private String itemName;
  private int itemQty;
  private boolean noted;

  public LoadoutItem(String itemName, int itemQty) {
    this.itemName = itemName;
    this.itemQty = itemQty;
    noted = false;
  }

  public LoadoutItem(String itemName, int itemQty, boolean noted) {
    this.itemName = itemName;
    this.itemQty = itemQty;
    this.noted = noted;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public int getItemQty() {
    return itemQty;
  }

  public void setItemQty(int itemQty) {
    this.itemQty = itemQty;
  }

  public boolean isNoted() {
    return noted;
  }

  public void setNoted(boolean noted) {
    this.noted = noted;
  }
}
