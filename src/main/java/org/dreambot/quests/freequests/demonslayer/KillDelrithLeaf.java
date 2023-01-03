package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.Client;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KillDelrithLeaf extends Leaf {

    private final HashMap<Integer, String> words = new HashMap<Integer, String>() {{
        put(0, "Carlem");
        put(1, "Aber");
        put(2, "Camerinthum");
        put(3, "Purchai");
        put(4, "Gabindo");
    }};

    private String[] incantationOrder;
    private ArrayList<String> incantationArrayList;

    private void getIncantationOrder() {
        incantationOrder = new String[]{
                words.get(PlayerSettings.getBitValue(2562)),
                words.get(PlayerSettings.getBitValue(2563)),
                words.get(PlayerSettings.getBitValue(2564)),
                words.get(PlayerSettings.getBitValue(2565)),
                words.get(PlayerSettings.getBitValue(2566)),
        };
    }

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2 &&
                PlayerSettings.getBitValue(2567) == 1;
    }

    @Override
    public int onLoop() {
        Area DELRITH_AREA = new Area(3224, 3373, 3232, 3366);
        if (!DELRITH_AREA.contains(Players.getLocal()) && !Client.isDynamicRegion()) {
            QuestHelper.walkToArea(DELRITH_AREA);
            return Timing.getSleepDelay();
        }

        if (Client.isInCutscene()) {
            if (Dialogues.inDialogue()) {
                if (Dialogues.canContinue()) {
                    Dialogues.continueDialogue();
                }
            }
            return Timing.loopReturn();
        }

        if (Inventory.contains("Silverlight")) {
            Inventory.interact("Silverlight", "Wield");
            Sleep.sleepUntil(() -> Equipment.contains("Silverlight"), 3000);
            return Timing.loopReturn();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                return Timing.loopReturn();
            }

            if (Dialogues.areOptionsAvailable()) {
                if (incantationOrder == null) {
                    getIncantationOrder();
                    incantationArrayList = new ArrayList<>(Arrays.asList(incantationOrder));
                }
                Dialogues.chooseOption(incantationArrayList.get(0));
                incantationArrayList.remove(0);
                Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 15000);
                return Timing.loopReturn();
            }
        }

        if (!Players.getLocal().isInCombat()) {
            NPC delrith = NPCs.closest("Delrith");
            if (delrith != null && delrith.interact("Attack")) {
                Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
            }
        }
        return Timing.loopReturn();
    }
}
