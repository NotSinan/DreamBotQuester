package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToWizardTraibornLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.DEMON_SLAYER.getVarBitID()) == 2 &&
                Inventory.containsAll(2401, 2400) &&
                Inventory.count("Bones") == 25;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(3099, 3168, 3117, 3154, 1),
                "Wizard Traiborn",
                new String[]{
                        "Talk about Demon Slayer.",
                        "I need to get a key given to you by Sir Prysin.",
                        "Well, have you got any keys knocking around?",
                        "I'll get the bones for you."
                });
    }
}
