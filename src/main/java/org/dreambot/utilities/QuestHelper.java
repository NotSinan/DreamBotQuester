package org.dreambot.utilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;

public class QuestHelper {

    public static int goAndTalkToNpc(Area area, String name, String[] dialogueOptions) {
        if (!area.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(area.getRandomTile());
            }
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            NPC npc = NPCs.closest(n -> n.getName().equals(name));
            if (npc != null && npc.interact("Talk-to")) {
                Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
            }
            return Timing.loopReturn();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }

            if (Dialogues.areOptionsAvailable()) {
                Dialogues.chooseFirstOptionContaining(dialogueOptions);
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }
        }

        return Timing.loopReturn();
    }

    public static int withdrawFromBank(String itemName, int quantity) {
        if (Bank.open()) {
            Bank.withdraw(itemName, quantity);
            Sleep.sleepUntil(() -> Inventory.contains(itemName) && Inventory.count(itemName) == quantity, 3000);
        }
        return Timing.loopReturn();
    }

    public static int purchaseFromShop(Area area, String itemName, int quantity, String npcName) {
        if (!area.contains(Players.getLocal())) {
            if (Walking.shouldWalk(4)) {
                Walking.walk(area.getRandomTile());
            }
        } else {
            if (Shop.isOpen()) {
                Shop.purchase(itemName, quantity);
                Sleep.sleepUntil(() -> Inventory.contains(itemName), 3000);
            } else {
                NPC shopAssistant = NPCs.closest(npcName);
                if (shopAssistant.interact("Trade")) {
                    Sleep.sleepUntil(() -> Shop.isOpen(), 3000);
                }
            }
        }
        return Timing.loopReturn();
    }
}
