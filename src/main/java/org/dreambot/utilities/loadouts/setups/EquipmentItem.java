package org.dreambot.utilities.loadouts.setups;


import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.utilities.impl.Condition;

public class EquipmentItem {
    private final String name;
    private final EquipmentSlot slot;
    private final int attLvl;
    private final int strLvl;
    private final int defLvl;
    private final Condition otherCondition;

    public EquipmentItem(String name, EquipmentSlot slot, int attLvl, int strLvl, int defLvl, Condition otherCondition) {
        this.name = name;
        this.slot = slot;
        this.attLvl = attLvl;
        this.strLvl = strLvl;
        this.defLvl = defLvl;
        this.otherCondition = otherCondition;
    }

    public String getName() {
        return name;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public int getAttLvl() {
        return attLvl;
    }

    public int getStrLvl() {
        return strLvl;
    }

    public int getDefLvl() {
        return defLvl;
    }

    public Condition getOtherCondition() {
        return otherCondition;
    }
}
