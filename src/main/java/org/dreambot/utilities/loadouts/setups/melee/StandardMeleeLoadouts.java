package org.dreambot.utilities.loadouts.setups.melee;

import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.utilities.loadouts.EquipmentLoadout;
import org.dreambot.utilities.loadouts.LoadoutItem;
import org.dreambot.utilities.OwnedItems;

import java.util.Arrays;
import java.util.Comparator;

public class StandardMeleeLoadouts {
    public static EquipmentLoadout getBestOwnedMeleeGear() {
        EquipmentLoadout loadout = new EquipmentLoadout();
        for (EquipmentSlot equipSlot : EquipmentSlot.values()) {
            // Get a list of all the equipment items applicable to this slot
            if (equipSlot.equals(EquipmentSlot.WEAPON)) {
                final int attLvl = Skills.getRealLevel(Skill.ATTACK);
                final int strLvl = Skills.getRealLevel(Skill.STRENGTH);
                StandardMeleeWeapon bestWep = Arrays.stream(StandardMeleeWeapon.values())
                        .filter(meleeWep -> attLvl >= meleeWep.getEquipmentItem().getAttLvl() &&
                                strLvl >= meleeWep.getEquipmentItem().getStrLvl() &&
                                OwnedItems.contains(meleeWep.getEquipmentItem().getName()) &&
                                meleeWep.getEquipmentItem().getOtherCondition().verify())
                        .max(Comparator.comparingInt(o -> o.getEquipmentItem().getAttLvl()))
                        .orElse(null);
                if (bestWep != null) {
                    loadout.addItem(new LoadoutItem(bestWep.getEquipmentItem().getName(), 1));
                }
                continue;
            }
            //armour slots
            final int defLvl = Skills.getRealLevel(Skill.DEFENCE);
            StandardMeleeArmour bestArmorForSlot = Arrays.stream(StandardMeleeArmour.values())
                    .filter(armourPiece -> defLvl >= armourPiece.getEquipmentItem().getDefLvl() &&
                            OwnedItems.contains(armourPiece.getEquipmentItem().getName()) &&
                            armourPiece.getEquipmentItem().getOtherCondition().verify())
                    .max(Comparator.comparingInt(o -> o.getEquipmentItem().getDefLvl()))
                    .orElse(null);
            if (bestArmorForSlot != null) {
                loadout.addItem(new LoadoutItem(bestArmorForSlot.getEquipmentItem().getName(), 1));
            }
        }
        return loadout;
    }
}
