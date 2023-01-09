package org.dreambot.quests.paidquests.clocktower;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.paidquests.clocktower.placecogs.CogState;
import org.dreambot.utilities.helpers.NPCHelper;

import java.util.Arrays;

public class TalkToBrotherKojoLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return CogState.finished() || Arrays.stream(new int[]{0, 5, 6, 7}).anyMatch(i -> i == PlayerSettings.getConfig(PaidQuest.CLOCK_TOWER.getConfigID()));
    }

    @Override
    public int onLoop() {
        return NPCHelper.goAndTalkToNpc(
                new Area(2563, 3255, 2574, 3239), //brother kojo area
                "Brother Kojo",
                new String[]{"OK old monk, what can I do?", "Yes."}
        );
    }
}
