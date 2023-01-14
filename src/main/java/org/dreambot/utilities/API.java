package org.dreambot.utilities;

import org.dreambot.api.methods.map.Tile;

public class API {

  public static QuestBranches selectedQuest;
  public static int questVarPlayer = 0;
  public static int questVarBit =
      0; // represents either VarBit if true, or VarPlayer (config) if false
  public static String currentBranch = "";
  public static String currentLeaf = "";
  public static String lastGameMessage = "";
  public static Tile lastGameMessageTile = null;
}
