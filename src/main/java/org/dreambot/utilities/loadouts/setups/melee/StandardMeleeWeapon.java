package org.dreambot.utilities.loadouts.setups.melee;

import java.util.function.Supplier;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.utilities.loadouts.EquipmentItem;

public enum StandardMeleeWeapon {
  BRONZE_SWORD(() -> new EquipmentItem("Bronze sword", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_SCIMITAR(
      () -> new EquipmentItem("Bronze scimitar", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_LONGSWORD(
      () -> new EquipmentItem("Bronze longsword", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_MACE(() -> new EquipmentItem("Bronze mace", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_BATTLEAXE(
      () -> new EquipmentItem("Bronze battleaxe", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_AXE(() -> new EquipmentItem("Bronze axe", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_PICKAXE(
      () -> new EquipmentItem("Bronze pickaxe", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_HASTA(() -> new EquipmentItem("Bronze hasta", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_WARHAMMER(
      () -> new EquipmentItem("Bronze warhammer", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_DAGGER(
      () -> new EquipmentItem("Bronze dagger", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_SPEAR(() -> new EquipmentItem("Bronze spear", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_CLAWS(
      () ->
          new EquipmentItem(
              "Bronze claws",
              EquipmentSlot.WEAPON,
              1,
              1,
              1,
              () -> PaidQuest.DEATH_PLATEAU.isFinished())),
  BRONZE_HALBERD(
      () -> new EquipmentItem("Bronze halberd", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  BRONZE_2H_SWORD(
      () -> new EquipmentItem("Bronze 2h sword", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_SWORD(() -> new EquipmentItem("Iron sword", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_SCIMITAR(
      () -> new EquipmentItem("Iron scimitar", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_LONGSWORD(
      () -> new EquipmentItem("Iron longsword", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_MACE(() -> new EquipmentItem("Iron mace", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_BATTLEAXE(
      () -> new EquipmentItem("Iron battleaxe", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_AXE(() -> new EquipmentItem("Iron axe", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_PICKAXE(() -> new EquipmentItem("Iron pickaxe", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_HASTA(() -> new EquipmentItem("Iron hasta", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_WARHAMMER(
      () -> new EquipmentItem("Iron warhammer", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_DAGGER(() -> new EquipmentItem("Iron dagger", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_SPEAR(() -> new EquipmentItem("Iron spear", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_CLAWS(
      () ->
          new EquipmentItem(
              "Iron claws",
              EquipmentSlot.WEAPON,
              1,
              1,
              1,
              () -> PaidQuest.DEATH_PLATEAU.isFinished())),
  IRON_HALBERD(() -> new EquipmentItem("Iron halberd", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  IRON_2H_SWORD(
      () -> new EquipmentItem("Iron 2h sword", EquipmentSlot.WEAPON, 1, 1, 1, () -> true)),
  STEEL_SWORD(() -> new EquipmentItem("Steel sword", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_SCIMITAR(
      () -> new EquipmentItem("Steel scimitar", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_LONGSWORD(
      () -> new EquipmentItem("Steel longsword", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_MACE(() -> new EquipmentItem("Steel mace", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_BATTLEAXE(
      () -> new EquipmentItem("Steel battleaxe", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_AXE(() -> new EquipmentItem("Steel axe", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_PICKAXE(
      () -> new EquipmentItem("Steel pickaxe", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_HASTA(() -> new EquipmentItem("Steel hasta", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_WARHAMMER(
      () -> new EquipmentItem("Steel warhammer", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_DAGGER(() -> new EquipmentItem("Steel dagger", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_SPEAR(() -> new EquipmentItem("Steel spear", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_CLAWS(
      () ->
          new EquipmentItem(
              "Steel claws",
              EquipmentSlot.WEAPON,
              5,
              1,
              1,
              () -> PaidQuest.DEATH_PLATEAU.isFinished())),
  STEEL_HALBERD(
      () -> new EquipmentItem("Steel halberd", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  STEEL_2H_SWORD(
      () -> new EquipmentItem("Steel 2h sword", EquipmentSlot.WEAPON, 5, 1, 1, () -> true)),
  BLACK_SWORD(() -> new EquipmentItem("Black sword", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_SCIMITAR(
      () -> new EquipmentItem("Black scimitar", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_LONGSWORD(
      () -> new EquipmentItem("Black longsword", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_MACE(() -> new EquipmentItem("Black mace", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_BATTLEAXE(
      () -> new EquipmentItem("Black battleaxe", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_AXE(() -> new EquipmentItem("Black axe", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_PICKAXE(
      () -> new EquipmentItem("Black pickaxe", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_HASTA(() -> new EquipmentItem("Black hasta", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_WARHAMMER(
      () -> new EquipmentItem("Black warhammer", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_DAGGER(() -> new EquipmentItem("Black dagger", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_SPEAR(() -> new EquipmentItem("Black spear", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_CLAWS(
      () ->
          new EquipmentItem(
              "Black claws",
              EquipmentSlot.WEAPON,
              10,
              1,
              1,
              () -> PaidQuest.DEATH_PLATEAU.isFinished())),
  BLACK_HALBERD(
      () -> new EquipmentItem("Black halberd", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  BLACK_2H_SWORD(
      () -> new EquipmentItem("Black 2h sword", EquipmentSlot.WEAPON, 10, 1, 1, () -> true)),
  MITHRIL_SWORD(
      () -> new EquipmentItem("Mithril sword", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_SCIMITAR(
      () -> new EquipmentItem("Mithril scimitar", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_LONGSWORD(
      () -> new EquipmentItem("Mithril longsword", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_MACE(() -> new EquipmentItem("Mithril mace", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_BATTLEAXE(
      () -> new EquipmentItem("Mithril battleaxe", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_AXE(() -> new EquipmentItem("Mithril axe", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_PICKAXE(
      () -> new EquipmentItem("Mithril pickaxe", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_HASTA(
      () -> new EquipmentItem("Mithril hasta", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_WARHAMMER(
      () -> new EquipmentItem("Mithril warhammer", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_DAGGER(
      () -> new EquipmentItem("Mithril dagger", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_SPEAR(
      () -> new EquipmentItem("Mithril spear", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_CLAWS(
      () ->
          new EquipmentItem(
              "Mithril claws",
              EquipmentSlot.WEAPON,
              20,
              1,
              1,
              () -> PaidQuest.DEATH_PLATEAU.isFinished())),
  MITHRIL_HALBERD(
      () -> new EquipmentItem("Mithril halberd", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  MITHRIL_2H_SWORD(
      () -> new EquipmentItem("Mithril 2h sword", EquipmentSlot.WEAPON, 20, 1, 1, () -> true)),
  ADAMANT_SWORD(
      () -> new EquipmentItem("Adamant sword", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_SCIMITAR(
      () -> new EquipmentItem("Adamant scimitar", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_LONGSWORD(
      () -> new EquipmentItem("Adamant longsword", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_MACE(() -> new EquipmentItem("Adamant mace", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_BATTLEAXE(
      () -> new EquipmentItem("Adamant battleaxe", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_AXE(() -> new EquipmentItem("Adamant axe", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_PICKAXE(
      () -> new EquipmentItem("Adamant pickaxe", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_HASTA(
      () -> new EquipmentItem("Adamant hasta", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_WARHAMMER(
      () -> new EquipmentItem("Adamant warhammer", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_DAGGER(
      () -> new EquipmentItem("Adamant dagger", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_SPEAR(
      () -> new EquipmentItem("Adamant spear", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_CLAWS(
      () ->
          new EquipmentItem(
              "Adamant claws",
              EquipmentSlot.WEAPON,
              30,
              1,
              1,
              () -> PaidQuest.DEATH_PLATEAU.isFinished())),
  ADAMANT_HALBERD(
      () -> new EquipmentItem("Adamant halberd", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  ADAMANT_2H_SWORD(
      () -> new EquipmentItem("Adamant 2h sword", EquipmentSlot.WEAPON, 30, 1, 1, () -> true)),
  RUNE_SWORD(() -> new EquipmentItem("Rune sword", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_SCIMITAR(
      () -> new EquipmentItem("Rune scimitar", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_LONGSWORD(
      () -> new EquipmentItem("Rune longsword", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_MACE(() -> new EquipmentItem("Rune mace", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_BATTLEAXE(
      () -> new EquipmentItem("Rune battleaxe", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_AXE(() -> new EquipmentItem("Rune axe", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_PICKAXE(() -> new EquipmentItem("Rune pickaxe", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_HASTA(() -> new EquipmentItem("Rune hasta", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_WARHAMMER(
      () -> new EquipmentItem("Rune warhammer", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_DAGGER(() -> new EquipmentItem("Rune dagger", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_SPEAR(() -> new EquipmentItem("Rune spear", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_CLAWS(
      () ->
          new EquipmentItem(
              "Rune claws",
              EquipmentSlot.WEAPON,
              40,
              1,
              1,
              () -> PaidQuest.DEATH_PLATEAU.isFinished())),
  RUNE_HALBERD(() -> new EquipmentItem("Rune halberd", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  RUNE_2H_SWORD(
      () -> new EquipmentItem("Rune 2h sword", EquipmentSlot.WEAPON, 40, 1, 1, () -> true)),
  DRAGON_SWORD(() -> new EquipmentItem("Dragon sword", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_SCIMITAR(
      () -> new EquipmentItem("Dragon scimitar", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_LONGSWORD(
      () -> new EquipmentItem("Dragon longsword", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_MACE(() -> new EquipmentItem("Dragon mace", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_BATTLEAXE(
      () -> new EquipmentItem("Dragon battleaxe", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_AXE(() -> new EquipmentItem("Dragon axe", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_PICKAXE(
      () -> new EquipmentItem("Dragon pickaxe", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_HASTA(() -> new EquipmentItem("Dragon hasta", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_WARHAMMER(
      () -> new EquipmentItem("Dragon warhammer", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_DAGGER(
      () -> new EquipmentItem("Dragon dagger", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_SPEAR(() -> new EquipmentItem("Dragon spear", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_CLAWS(
      () ->
          new EquipmentItem(
              "Dragon claws",
              EquipmentSlot.WEAPON,
              60,
              1,
              1,
              () -> PaidQuest.DEATH_PLATEAU.isFinished())),
  DRAGON_HALBERD(
      () -> new EquipmentItem("Dragon halberd", EquipmentSlot.WEAPON, 60, 1, 1, () -> true)),
  DRAGON_2H_SWORD(
      () -> new EquipmentItem("Dragon 2h sword", EquipmentSlot.WEAPON, 60, 1, 1, () -> true));

  private final Supplier<EquipmentItem> equipmentItemSupplier;

  StandardMeleeWeapon(Supplier<EquipmentItem> equipmentItemSupplier) {
    this.equipmentItemSupplier = equipmentItemSupplier;
  }

  public EquipmentItem getEquipmentItem() {
    return equipmentItemSupplier.get();
  }
}
