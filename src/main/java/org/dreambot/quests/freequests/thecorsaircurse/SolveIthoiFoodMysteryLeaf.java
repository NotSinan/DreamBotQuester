package org.dreambot.quests.freequests.thecorsaircurse;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.helpers.NPCHelper;

public class SolveIthoiFoodMysteryLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 35 ||
                PlayerSettings.getBitValue(FreeQuest.CORSAIR_CURSE.getVarBitID()) == 40;
    }

    @Override
    public int onLoop() {
        return NPCHelper.goAndTalkToNpc(
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
