package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;
import org.dreambot.utilities.Timing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KillDelrithLeaf extends Leaf {


    private String[] incantationOrder;
    private ArrayList<String> incantationArrayList;

    private void getIncantationOrder() {
        final HashMap<Integer, String> words = new HashMap<Integer, String>() {{
            put(0, "Carlem");
            put(1, "Aber");
            put(2, "Camerinthum");
            put(3, "Purchai");
            put(4, "Gabindo");
        }};
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
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 2 &&
                PlayerSettings.getBitValue(2567) == 1;
    }

    @Override
    public int onLoop() {
        if (QuestHelper.inCutscene()) {
            Logger.log("In cutscene of KillDelrithLeaf");
            if (Dialogues.inDialogue()) {
                if (Dialogues.canContinue()) {
                    if (Dialogues.continueDialogue()) {
                        Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 3000);
                    }
                }
            }
            return Timing.loopReturn();
        }

        final Area DELRITH_AREA = new Area(3224, 3373, 3232, 3366);
        if (!QuestHelper.walkToArea(DELRITH_AREA)) {
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
            }

            if (Dialogues.areOptionsAvailable()) {
                if (incantationOrder == null) {
                    getIncantationOrder();
                    incantationArrayList = new ArrayList<>(Arrays.asList(incantationOrder));
                }
                if (Dialogues.chooseOption(incantationArrayList.get(0))) {
                    incantationArrayList.remove(0);
                    Sleep.sleepUntil(() -> !Dialogues.isProcessing(), 15000);
                }

                return Timing.loopReturn();
            }
        }

        if (!Players.getLocal().isInCombat()) {
            NPC delrith = NPCs.closest("Delrith");
            if (delrith != null && Interaction.delayEntityInteract(delrith, "Attack")) {
                Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 5000);
            }
        }

        return Timing.loopReturn();
    }
}
