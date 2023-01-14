package org.dreambot.utilities.loadouts.setups.melee;

import java.util.function.Supplier;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.utilities.loadouts.EquipmentItem;

public enum StandardMeleeArmour {
  BRONZE_BOOTS(() -> new EquipmentItem("Bronze boots", EquipmentSlot.FEET, 1, 1, 1, () -> true)),
  BRONZE_GLOVES(() -> new EquipmentItem("Bronze gloves", EquipmentSlot.HANDS, 1, 1, 1, () -> true)),
  BRONZE_DEFENDER(
      () -> new EquipmentItem("Bronze defender", EquipmentSlot.SHIELD, 1, 1, 1, () -> true)),
  BRONZE_KITESHIELD(
      () -> new EquipmentItem("Bronze kiteshield", EquipmentSlot.SHIELD, 1, 1, 1, () -> true)),
  BRONZE_MED_HELM(
      () -> new EquipmentItem("Bronze med helm", EquipmentSlot.HAT, 1, 1, 1, () -> true)),
  BRONZE_FULL_HELM(
      () -> new EquipmentItem("Bronze full helm", EquipmentSlot.HAT, 1, 1, 1, () -> true)),
  BRONZE_SQ_SHIELD(
      () -> new EquipmentItem("Bronze sq shield", EquipmentSlot.SHIELD, 1, 1, 1, () -> true)),
  BRONZE_PLATELEGS(
      () -> new EquipmentItem("Bronze platelegs", EquipmentSlot.LEGS, 1, 1, 1, () -> true)),
  BRONZE_PLATESKIRT(
      () -> new EquipmentItem("Bronze plateskirt", EquipmentSlot.LEGS, 1, 1, 1, () -> true)),
  BRONZE_PLATEBODY(
      () -> new EquipmentItem("Bronze platebody", EquipmentSlot.CHEST, 1, 1, 1, () -> true)),
  BRONZE_CHAINBODY(
      () -> new EquipmentItem("Bronze chainbody", EquipmentSlot.CHEST, 1, 1, 1, () -> true)),
  IRON_BOOTS(() -> new EquipmentItem("Iron boots", EquipmentSlot.FEET, 1, 1, 1, () -> true)),
  IRON_GLOVES(() -> new EquipmentItem("Iron gloves", EquipmentSlot.HANDS, 1, 1, 1, () -> true)),
  IRON_DEFENDER(
      () -> new EquipmentItem("Iron defender", EquipmentSlot.SHIELD, 1, 1, 1, () -> true)),
  IRON_KITESHIELD(
      () -> new EquipmentItem("Iron kiteshield", EquipmentSlot.SHIELD, 1, 1, 1, () -> true)),
  IRON_MED_HELM(() -> new EquipmentItem("Iron med helm", EquipmentSlot.HAT, 1, 1, 1, () -> true)),
  IRON_FULL_HELM(() -> new EquipmentItem("Iron full helm", EquipmentSlot.HAT, 1, 1, 1, () -> true)),
  IRON_SQ_SHIELD(
      () -> new EquipmentItem("Iron sq shield", EquipmentSlot.SHIELD, 1, 1, 1, () -> true)),
  IRON_PLATELEGS(
      () -> new EquipmentItem("Iron platelegs", EquipmentSlot.LEGS, 1, 1, 1, () -> true)),
  IRON_PLATESKIRT(
      () -> new EquipmentItem("Iron plateskirt", EquipmentSlot.LEGS, 1, 1, 1, () -> true)),
  IRON_PLATEBODY(
      () -> new EquipmentItem("Iron platebody", EquipmentSlot.CHEST, 1, 1, 1, () -> true)),
  IRON_CHAINBODY(
      () -> new EquipmentItem("Iron chainbody", EquipmentSlot.CHEST, 1, 1, 1, () -> true)),
  STEEL_BOOTS(() -> new EquipmentItem("Steel boots", EquipmentSlot.FEET, 1, 1, 5, () -> true)),
  STEEL_GLOVES(() -> new EquipmentItem("Steel gloves", EquipmentSlot.HANDS, 1, 1, 5, () -> true)),
  STEEL_DEFENDER(
      () -> new EquipmentItem("Steel defender", EquipmentSlot.SHIELD, 1, 1, 5, () -> true)),
  STEEL_KITESHIELD(
      () -> new EquipmentItem("Steel kiteshield", EquipmentSlot.SHIELD, 1, 1, 5, () -> true)),
  STEEL_MED_HELM(() -> new EquipmentItem("Steel med helm", EquipmentSlot.HAT, 1, 1, 5, () -> true)),
  STEEL_FULL_HELM(
      () -> new EquipmentItem("Steel full helm", EquipmentSlot.HAT, 1, 1, 5, () -> true)),
  STEEL_SQ_SHIELD(
      () -> new EquipmentItem("Steel sq shield", EquipmentSlot.SHIELD, 1, 1, 5, () -> true)),
  STEEL_PLATELEGS(
      () -> new EquipmentItem("Steel platelegs", EquipmentSlot.LEGS, 1, 1, 5, () -> true)),
  STEEL_PLATESKIRT(
      () -> new EquipmentItem("Steel plateskirt", EquipmentSlot.LEGS, 1, 1, 5, () -> true)),
  STEEL_PLATEBODY(
      () -> new EquipmentItem("Steel platebody", EquipmentSlot.CHEST, 1, 1, 5, () -> true)),
  STEEL_CHAINBODY(
      () -> new EquipmentItem("Steel chainbody", EquipmentSlot.CHEST, 1, 1, 5, () -> true)),
  BLACK_BOOTS(() -> new EquipmentItem("Black boots", EquipmentSlot.FEET, 1, 1, 10, () -> true)),
  BLACK_GLOVES(() -> new EquipmentItem("Black gloves", EquipmentSlot.HANDS, 1, 1, 10, () -> true)),
  BLACK_DEFENDER(
      () -> new EquipmentItem("Black defender", EquipmentSlot.SHIELD, 1, 1, 10, () -> true)),
  BLACK_KITESHIELD(
      () -> new EquipmentItem("Black kiteshield", EquipmentSlot.SHIELD, 1, 1, 10, () -> true)),
  BLACK_MED_HELM(
      () -> new EquipmentItem("Black med helm", EquipmentSlot.HAT, 1, 1, 10, () -> true)),
  BLACK_FULL_HELM(
      () -> new EquipmentItem("Black full helm", EquipmentSlot.HAT, 1, 1, 10, () -> true)),
  BLACK_SQ_SHIELD(
      () -> new EquipmentItem("Black sq shield", EquipmentSlot.SHIELD, 1, 1, 10, () -> true)),
  BLACK_PLATELEGS(
      () -> new EquipmentItem("Black platelegs", EquipmentSlot.LEGS, 1, 1, 10, () -> true)),
  BLACK_PLATESKIRT(
      () -> new EquipmentItem("Black plateskirt", EquipmentSlot.LEGS, 1, 1, 10, () -> true)),
  BLACK_PLATEBODY(
      () -> new EquipmentItem("Black platebody", EquipmentSlot.CHEST, 1, 1, 10, () -> true)),
  BLACK_CHAINBODY(
      () -> new EquipmentItem("Black chainbody", EquipmentSlot.CHEST, 1, 1, 10, () -> true)),
  MITHRIL_BOOTS(() -> new EquipmentItem("Mithril boots", EquipmentSlot.FEET, 1, 1, 20, () -> true)),
  MITHRIL_GLOVES(
      () -> new EquipmentItem("Mithril gloves", EquipmentSlot.HANDS, 1, 1, 20, () -> true)),
  MITHRIL_DEFENDER(
      () -> new EquipmentItem("Mithril defender", EquipmentSlot.SHIELD, 1, 1, 20, () -> true)),
  MITHRIL_KITESHIELD(
      () -> new EquipmentItem("Mithril kiteshield", EquipmentSlot.SHIELD, 1, 1, 20, () -> true)),
  MITHRIL_MED_HELM(
      () -> new EquipmentItem("Mithril med helm", EquipmentSlot.HAT, 1, 1, 20, () -> true)),
  MITHRIL_FULL_HELM(
      () -> new EquipmentItem("Mithril full helm", EquipmentSlot.HAT, 1, 1, 20, () -> true)),
  MITHRIL_SQ_SHIELD(
      () -> new EquipmentItem("Mithril sq shield", EquipmentSlot.SHIELD, 1, 1, 20, () -> true)),
  MITHRIL_PLATELEGS(
      () -> new EquipmentItem("Mithril platelegs", EquipmentSlot.LEGS, 1, 1, 20, () -> true)),
  MITHRIL_PLATESKIRT(
      () -> new EquipmentItem("Mithril plateskirt", EquipmentSlot.LEGS, 1, 1, 20, () -> true)),
  MITHRIL_PLATEBODY(
      () -> new EquipmentItem("Mithril platebody", EquipmentSlot.CHEST, 1, 1, 20, () -> true)),
  MITHRIL_CHAINBODY(
      () -> new EquipmentItem("Mithril chainbody", EquipmentSlot.CHEST, 1, 1, 20, () -> true)),
  ADAMANT_BOOTS(() -> new EquipmentItem("Adamant boots", EquipmentSlot.FEET, 1, 1, 30, () -> true)),
  ADAMANT_GLOVES(
      () -> new EquipmentItem("Adamant gloves", EquipmentSlot.HANDS, 1, 1, 30, () -> true)),
  ADAMANT_DEFENDER(
      () -> new EquipmentItem("Adamant defender", EquipmentSlot.SHIELD, 1, 1, 30, () -> true)),
  ADAMANT_KITESHIELD(
      () -> new EquipmentItem("Adamant kiteshield", EquipmentSlot.SHIELD, 1, 1, 30, () -> true)),
  ADAMANT_MED_HELM(
      () -> new EquipmentItem("Adamant med helm", EquipmentSlot.HAT, 1, 1, 30, () -> true)),
  ADAMANT_FULL_HELM(
      () -> new EquipmentItem("Adamant full helm", EquipmentSlot.HAT, 1, 1, 30, () -> true)),
  ADAMANT_SQ_SHIELD(
      () -> new EquipmentItem("Adamant sq shield", EquipmentSlot.SHIELD, 1, 1, 30, () -> true)),
  ADAMANT_PLATELEGS(
      () -> new EquipmentItem("Adamant platelegs", EquipmentSlot.LEGS, 1, 1, 30, () -> true)),
  ADAMANT_PLATESKIRT(
      () -> new EquipmentItem("Adamant plateskirt", EquipmentSlot.LEGS, 1, 1, 30, () -> true)),
  ADAMANT_PLATEBODY(
      () -> new EquipmentItem("Adamant platebody", EquipmentSlot.CHEST, 1, 1, 30, () -> true)),
  ADAMANT_CHAINBODY(
      () -> new EquipmentItem("Adamant chainbody", EquipmentSlot.CHEST, 1, 1, 30, () -> true)),
  RUNE_BOOTS(() -> new EquipmentItem("Rune boots", EquipmentSlot.FEET, 1, 1, 40, () -> true)),
  RUNE_GLOVES(() -> new EquipmentItem("Rune gloves", EquipmentSlot.HANDS, 1, 1, 40, () -> true)),
  RUNE_DEFENDER(
      () -> new EquipmentItem("Rune defender", EquipmentSlot.SHIELD, 1, 1, 40, () -> true)),
  RUNE_KITESHIELD(
      () -> new EquipmentItem("Rune kiteshield", EquipmentSlot.SHIELD, 1, 1, 40, () -> true)),
  RUNE_MED_HELM(() -> new EquipmentItem("Rune med helm", EquipmentSlot.HAT, 1, 1, 40, () -> true)),
  RUNE_FULL_HELM(
      () -> new EquipmentItem("Rune full helm", EquipmentSlot.HAT, 1, 1, 40, () -> true)),
  RUNE_SQ_SHIELD(
      () -> new EquipmentItem("Rune sq shield", EquipmentSlot.SHIELD, 1, 1, 40, () -> true)),
  RUNE_PLATELEGS(
      () -> new EquipmentItem("Rune platelegs", EquipmentSlot.LEGS, 1, 1, 40, () -> true)),
  RUNE_PLATESKIRT(
      () -> new EquipmentItem("Rune plateskirt", EquipmentSlot.LEGS, 1, 1, 40, () -> true)),
  RUNE_PLATEBODY(
      () -> new EquipmentItem("Rune platebody", EquipmentSlot.CHEST, 1, 1, 40, () -> true)),
  RUNE_CHAINBODY(
      () -> new EquipmentItem("Rune chainbody", EquipmentSlot.CHEST, 1, 1, 40, () -> true)),
  DRAGON_BOOTS(() -> new EquipmentItem("Dragon boots", EquipmentSlot.FEET, 1, 1, 60, () -> true)),
  DRAGON_GLOVES(
      () -> new EquipmentItem("Dragon gloves", EquipmentSlot.HANDS, 1, 1, 60, () -> true)),
  DRAGON_DEFENDER(
      () -> new EquipmentItem("Dragon defender", EquipmentSlot.SHIELD, 1, 1, 60, () -> true)),
  DRAGON_KITESHIELD(
      () -> new EquipmentItem("Dragon kiteshield", EquipmentSlot.SHIELD, 1, 1, 60, () -> true)),
  DRAGON_MED_HELM(
      () -> new EquipmentItem("Dragon med helm", EquipmentSlot.HAT, 1, 1, 60, () -> true)),
  DRAGON_FULL_HELM(
      () -> new EquipmentItem("Dragon full helm", EquipmentSlot.HAT, 1, 1, 60, () -> true)),
  DRAGON_SQ_SHIELD(
      () -> new EquipmentItem("Dragon sq shield", EquipmentSlot.SHIELD, 1, 1, 60, () -> true)),
  DRAGON_PLATELEGS(
      () -> new EquipmentItem("Dragon platelegs", EquipmentSlot.LEGS, 1, 1, 60, () -> true)),
  DRAGON_PLATESKIRT(
      () -> new EquipmentItem("Dragon plateskirt", EquipmentSlot.LEGS, 1, 1, 60, () -> true)),
  DRAGON_PLATEBODY(
      () -> new EquipmentItem("Dragon platebody", EquipmentSlot.CHEST, 1, 1, 60, () -> true)),
  DRAGON_CHAINBODY(
      () -> new EquipmentItem("Dragon chainbody", EquipmentSlot.CHEST, 1, 1, 60, () -> true));

  private final Supplier<EquipmentItem> equipmentItemSupplier;

  StandardMeleeArmour(Supplier<EquipmentItem> equipmentItemSupplier) {
    this.equipmentItemSupplier = equipmentItemSupplier;
  }

  public EquipmentItem getEquipmentItem() {
    return equipmentItemSupplier.get();
  }
}
