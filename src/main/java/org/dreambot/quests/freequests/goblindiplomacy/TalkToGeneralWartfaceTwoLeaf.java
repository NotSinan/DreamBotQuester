package org.dreambot.quests.freequests.goblindiplomacy;

import org.dreambot.api.Client;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.Timing;

public class TalkToGeneralWartfaceTwoLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 3 ||
                PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 4 ||
                PlayerSettings.getBitValue(FreeQuest.GOBLIN_DIPLOMACY.getVarBitID()) == 5;
    }

    @Override
    public int onLoop() {

        final Area GENERAL_WARTFACE_AREA = new Area(
                new Tile[]{
                        new Tile(2954, 3510, 0),
                        new Tile(2961, 3510, 0),
                        new Tile(2961, 3514, 0),
                        new Tile(2957, 3514, 0),
                        new Tile(2954, 3512, 0)
                }
        );

        if (!GENERAL_WARTFACE_AREA.contains(Players.getLocal()) && !Client.isDynamicRegion()) {
            QuestHelper.walkToArea(GENERAL_WARTFACE_AREA);
            return Timing.loopReturn();
        }

        if (!Dialogues.inDialogue()) {
            NPC generalWartface = NPCs.closest("General Wartface");
            generalWartface.interact("Talk-to");
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), 3000);
            return Timing.loopReturn();
        }

        if (Dialogues.inDialogue()) {
            if (Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                return Timing.loopReturn();
            }

            if (Dialogues.areOptionsAvailable()) {
                Dialogues.chooseFirstOption("I have some blue armour here.", "I have some brown armour here.", "No he doesn't look fat");
                return Timing.loopReturn();
            }
        }

        if (Client.isDynamicRegion()) {
            if (Dialogues.inDialogue()) {
                if (Dialogues.canContinue()) {
                    Dialogues.continueDialogue();
                }
            }
        }
        return Timing.loopReturn();
    }
}
