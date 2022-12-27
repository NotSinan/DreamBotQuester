package org.dreambot.quests.witchspotion;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.widget.helpers.ItemProcessing;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;
import org.dreambot.utilities.Timing;

public class RetrieveEyeOfNewt extends Leaf {
    private final Area PORT_SARIM_MAGE_SHOP_AREA = new Area(3011, 3261, 3016, 3256, 0);

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_WITCHS_POTION.getId()) == 1 && !Inventory.contains("Eye of newt");
    }

    @Override
    public int onLoop() {
        if (Inventory.count("Coins") < 3) {
            return withdraw5Coins();
        }
        return QuestHelper.purchaseFromShop(PORT_SARIM_MAGE_SHOP_AREA, "Eye of newt", 1, "Betty");
    }
    private int withdraw5Coins() {
        if(Bank.getLastBankHistoryCacheTime() <= 0) {
            Bank.open(BankLocation.getNearest());
            return Timing.loopReturn();
        }

        if(Bank.count("Coins") < 5) {
            Logger.log("Stopping script due to lack of coins! Total coins (bank + inventory): "+
                    (Bank.count("Coins") + Inventory.count("Coins")));
            return -1;
        }

        if(Bank.open(BankLocation.getNearest())) {
            Timing.sleepForDelay();
            if(Bank.withdraw("Coins",5)) {
                Sleep.sleepUntil(() -> Inventory.count("Coins") >= 5, 3000);
            }
        }
        return Timing.loopReturn();
    }
}
