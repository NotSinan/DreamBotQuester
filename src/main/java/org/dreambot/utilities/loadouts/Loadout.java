package org.dreambot.utilities.loadouts;


import org.dreambot.api.utilities.Logger;

public class Loadout {
    private EquipmentLoadout equipmentLoadout;
    private InventoryLoadout inventoryLoadout;

    public Loadout(EquipmentLoadout equipmentLoadout, InventoryLoadout inventoryLoadout) {
        this.equipmentLoadout = equipmentLoadout;
        this.inventoryLoadout = inventoryLoadout;
    }

    public boolean fulfill() {
        if (equipmentLoadout == null || equipmentLoadout.fulfill() &&
            inventoryLoadout == null || inventoryLoadout.fulfill()) {
            Logger.log("Equipment + inventory loadout fulfilled successfully within timeout!");
            return true;
        }
        return false;
    }
}
