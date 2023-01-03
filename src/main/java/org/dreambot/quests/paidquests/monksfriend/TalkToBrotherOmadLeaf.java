package org.dreambot.quests.paidquests.monksfriend;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarPlayer;

public class TalkToBrotherOmadLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarPlayer.QUEST_MONKS_FRIEND.getId()) == 0 ||
                PlayerSettings.getConfig(QuestVarPlayer.QUEST_MONKS_FRIEND.getId()) == 10 && Inventory.contains("Child's blanket");
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
                new Area(2602, 3211, 2610, 3207),
                "Brother Omad",
                new String[]{"Why can't you sleep, what's wrong?", "Can I help at all?", "Yes."}
        );
    }
}
