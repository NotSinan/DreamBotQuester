package org.dreambot.quests.freequests.therestlessghost;

import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToFatherUrhneyLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_THE_RESTLESS_GHOST.getId()) == 1 && !Equipment.contains("Ghostspeak amulet");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(3144, 3177, 3151, 3173),
                "Father Urhney",
                new String[]{ "Father Aereck sent me to talk to you.", "He's got a ghost haunting his graveyard." }
        );
    }
}
