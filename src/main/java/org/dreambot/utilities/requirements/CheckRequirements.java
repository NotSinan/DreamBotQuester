package org.dreambot.utilities.requirements;

import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.utilities.API;

public class CheckRequirements {
  public static boolean checkRequirements(Quest quest) {
    if (!quest.hasRequirements()) {
      Logger.log("Do not have requirements to start " + quest + ", stopping script...!");
      ScriptManager.getScriptManager().stop();
      return false;
    }

    if (quest.isFinished()) {
      Logger.log("Finished " + quest + ", stopping script...!");
      ScriptManager.getScriptManager().stop();
      return false;
    }

    if (API.questVarBit != quest.getVarBitID()) {
      API.questVarBit = quest.getVarBitID();
    }

    if (API.questVarPlayer != quest.getConfigID()) {
      API.questVarPlayer = quest.getConfigID();
    }

    return true;
  }
}
