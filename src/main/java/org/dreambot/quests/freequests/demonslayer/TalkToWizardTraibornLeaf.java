package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToWizardTraibornLeaf extends Leaf {


    private final Area WIZARD_TRAIBORN_AREA = new Area(3099, 3168, 3117, 3154, 1);
    private final String[] DIALOGUE_OPTIONS = {
            "Talk about Demon Slayer.",
            "I need to get a key given to you by Sir Prysin.",
            "Well, have you got any keys knocking around?",
            "I'll get the bones for you."
    };
    private final String WIZARD_TRAIBORN = "Wizard Traiborn";


    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 2 &&
                Inventory.containsAll(2401, 2400) &&
                Inventory.count("Bones") == 25;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(WIZARD_TRAIBORN_AREA, WIZARD_TRAIBORN, DIALOGUE_OPTIONS);
    }
}