package org.dreambot.quests.freequests.thecorsaircurse.cursepuzzle;

import org.dreambot.api.methods.settings.PlayerSettings;

public class CurseState {
  private static final int TALK_TO_ITHOI_VARBIT = 6075;
  private static final int TALK_TO_ARSEN_VARBIT = 6074;
  private static final int TALK_TO_COLIN_VARBIT = 6072;
  private static final int TALK_TO_GNOCCI_VARBIT = 6073;

  public static boolean talkedToIthoi() {
    return PlayerSettings.getBitValue(TALK_TO_ITHOI_VARBIT) == 1;
  }

  public static boolean talkedToArsen() {
    return PlayerSettings.getBitValue(TALK_TO_ARSEN_VARBIT) >= 2;
  }

  public static boolean returnedToothpick() {
    return PlayerSettings.getBitValue(TALK_TO_ARSEN_VARBIT) == 4;
  }

  public static boolean finishedArsen() {
    return PlayerSettings.getBitValue(TALK_TO_ARSEN_VARBIT) >= 6;
  }

  public static boolean talkedToColin() {
    return PlayerSettings.getBitValue(TALK_TO_COLIN_VARBIT) >= 1;
  }

  public static boolean lookedThroughTelescope() {
    return PlayerSettings.getBitValue(TALK_TO_COLIN_VARBIT) == 2;
  }

  public static boolean finishedColin() {
    return PlayerSettings.getBitValue(TALK_TO_COLIN_VARBIT) == 3;
  }

  public static boolean talkedToGnocci() {
    return PlayerSettings.getBitValue(TALK_TO_GNOCCI_VARBIT) == 1;
  }

  public static boolean foundDoll() {
    return PlayerSettings.getBitValue(TALK_TO_GNOCCI_VARBIT) == 2;
  }

  public static boolean finishedGnocci() {
    return PlayerSettings.getBitValue(TALK_TO_GNOCCI_VARBIT) == 3;
  }
}
