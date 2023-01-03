package org.dreambot.quests.paidquests.monksfriend;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.QuestHelper;

public class TalkToBrotherOmadLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(PaidQuest.MONKS_FRIEND.getConfigID()) == 0 ||
                PlayerSettings.getConfig(PaidQuest.MONKS_FRIEND.getConfigID()) == 10 && Inventory.contains("Child's blanket");
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
