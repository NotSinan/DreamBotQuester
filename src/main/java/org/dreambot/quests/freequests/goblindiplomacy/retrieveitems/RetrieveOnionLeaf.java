package org.dreambot.quests.freequests.goblindiplomacy.retrieveitems;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.GameObjectHelper;

public class RetrieveOnionLeaf extends Leaf {
  @Override
  public boolean isValid() {
    return PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 0
        && Inventory.count("Onion") < 2
        && !Inventory.contains("Yellow dye")
        && !Inventory.contains("Orange dye")
        && !Inventory.contains("Orange goblin mail");
  }

  @Override
  public int onLoop() {
    final Area ONION_AREA = new Area(3186, 3269, 3191, 3265);
    final int count = Inventory.count("Onion") + 1;
    return GameObjectHelper.goAndInteractWithGameObject(
        ONION_AREA, "Onion", "Pick", () -> Inventory.count("Onion") != count);
  }
}
