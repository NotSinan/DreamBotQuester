package org.dreambot.utilities.loadouts;


public class Loadout {
    private EquipmentLoadout equipmentLoadout;
    private InventoryLoadout inventoryLoadout;

    public Loadout(EquipmentLoadout equipmentLoadout, InventoryLoadout inventoryLoadout) {
        this.equipmentLoadout = equipmentLoadout;
        this.inventoryLoadout = inventoryLoadout;
    }
    public boolean fulfilled() {
        return (equipmentLoadout == null || equipmentLoadout.fulfilled()) &&
                (inventoryLoadout == null || inventoryLoadout.fulfilled());
    }
    public boolean fulfill() {
        return (equipmentLoadout == null || equipmentLoadout.fulfill()) &&
                (inventoryLoadout == null || inventoryLoadout.fulfill());
    }

    public void addInventoryItem(LoadoutItem item) {
        if (inventoryLoadout == null) {
            inventoryLoadout = new InventoryLoadout();
        }
        inventoryLoadout.addItem(item);
    }
    public void addEquipmentItem(LoadoutItem item) {
        if (equipmentLoadout == null) {
            equipmentLoadout = new EquipmentLoadout();
        }
        equipmentLoadout.addItem(item);
    }
}
