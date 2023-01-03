package org.dreambot.quests.paidquests.clocktower.placecogs;

public class CogState {
    public static boolean placedRedCog = false;
    public static boolean placedBlackCog = false;
    public static boolean placedWhiteCog = false;
    public static boolean placedBlueCog = false;

    public static boolean finished() {
        return placedBlackCog && placedBlueCog && placedRedCog && placedWhiteCog;
    }

}
