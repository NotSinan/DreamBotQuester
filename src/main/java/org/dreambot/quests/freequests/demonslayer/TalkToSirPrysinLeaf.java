package org.dreambot.quests.freequests.demonslayer;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class TalkToSirPrysinLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 1 ||
                PlayerSettings.getBitValue(QuestVarBits.QUEST_DEMON_SLAYER.getId()) == 2 && Inventory.containsAll(2400, 2399, 2401);
    }

    @Override
    public int onLoop() {
        final Area SIR_PRYSIN_AREA = new Area(3201, 3475, 3206, 3469);
        final String[] DIALOGUE_OPTIONS = {
                "Aris said I should come and talk to you.",
                "I need to find Silverlight.",
                "He's back and unfortunately I've got to deal with him.",
                "So give me the keys!",
                "Can you give me your key?"
        };
        final String SIR_PRYSIN = "Sir Prysin";
        return QuestHelper.goAndTalkToNpc(SIR_PRYSIN_AREA, SIR_PRYSIN, DIALOGUE_OPTIONS);
    }
}
