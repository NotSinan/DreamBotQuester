package org.dreambot.utilities;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.utilities.Sleep;

public class Timing {
    // Variables to hold our various timings
    public static int tickTimeout = 1;
    public static long sleepLength = 100;
    // Sleep Settings
    public static int sleepMin = 25;
    public static int sleepMax = 100;
    public static int sleepDeviation = 20;
    public static int sleepTarget = 140;
    public static boolean sleepWeightedDistribution = true;
    // Tick Settings
    public static int tickDelayMin = 1;
    public static int tickDelayMax = 3;
    public static int tickDelayDeviation = 1;
    public static int tickDelayTarget = 2;
    public static boolean tickDelayWeightedDistribution = true;

    public static boolean isValidTick() {
        return tickTimeout == 0;
    }

    public static int loopReturn() {
        if (tickTimeout <= 0) tickTimeout += getTickDelay();
        Sleep.sleepTick();
        return 60;
    }

    // Sleep for the set delay and re-calculate it
    public static void sleepForDelay() {
        Sleep.sleep(sleepLength);
        setSleepDelay();
    }

    // Calculate new tick delay and sleep for it
    public static void sleepForTickDelay() {
        Sleep.sleepTicks(getTickDelay());
    }

    // Get a randomized sleep delay
    public static void setSleepDelay() {
        sleepLength = getRandomDelay(sleepWeightedDistribution, sleepMin, sleepMax, sleepDeviation, sleepTarget);
    }
    public static int getSleepDelay() {
        return (int) getRandomDelay(sleepWeightedDistribution, sleepMin, sleepMax, sleepDeviation, sleepTarget);
    }

    // Get a randomized timeout delay
    public static int getTickDelay() {
        return (int) getRandomDelay(tickDelayWeightedDistribution, tickDelayMin, tickDelayMax, tickDelayDeviation, tickDelayTarget);
    }

    //Ganom's function, generates a random number allowing for curve and weight
    public static long getRandomDelay(boolean weightedDistribution, int min, int max, int deviation, int target) {
        if (weightedDistribution) {
            /* generate a gaussian random (average at 0.0, std dev of 1.0)
             * take the absolute value of it (if we don't, every negative value will be clamped at the minimum value)
             * get the log base e of it to make it shifted towards the right side
             * invert it to shift the distribution to the other end
             * clamp it to min max, any values outside of range are set to min or max */
            return (long) clamp((-Math.log(Math.abs(Calculations.nextGaussianRandom(0.0, 1.0)))) * deviation + target, min, max);
        } else {
            /* generate a normal even distribution random */
            return (long) clamp(Math.round(Calculations.nextGaussianRandom(0.0, 1.0) * deviation + target), min, max);
        }
    }

    // Standard clamping function
    private static double clamp(double val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
