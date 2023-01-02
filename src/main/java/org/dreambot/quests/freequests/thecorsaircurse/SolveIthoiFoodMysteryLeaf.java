package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle.CurseState;
import org.dreambot.utilities.QuestHelper;
import org.dreambot.utilities.QuestVarBits;

public class SolveIthoiFoodMysteryLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 35 ||
                PlayerSettings.getConfig(QuestVarBits.QUEST_THE_CORSAIR_CURSE.getId()) == 40;
    }

    @Override
    public int onLoop() {
        return QuestHelper.goAndTalkToNpc(
            new Area(2527, 2841, 2532, 2835, 1),
            "Ithoi the Navigator",
            new String[]{
                "I bet I can prove you're well enough to get up.",
                "I know you've faked the curse.",
                "I hear you cooked the meal they ate before getting sick.",
                "Maybe because the Captain's thinking of firing you."
        });
    }
}
