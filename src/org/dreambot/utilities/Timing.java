package org.dreambot.utilities;

import org.dreambot.api.methods.Calculations;

public class Timing {
    // Variables to hold our various timings
    public static int tickTimeout = 3;
    public static long sleepLength = 100;
    // Sleep Settings
    public static int sleepMin = 60;
    public static int sleepMax = 350;
    public static int sleepDeviation = 10;
    public static int sleepTarget = 100;
    public static boolean sleepWeightedDistribution = false;
    // Tick Settings
    public static int tickDelayMin = 1;
    public static int tickDelayMax = 5;
    public static int tickDelayDeviation = 1;
    public static int tickDelayTarget = 3;
    public static boolean tickDelayWeightedDistribution = false;

    public static boolean isValidTick() {
        return tickTimeout == 0;
    }

    public static int loopReturn() {
        tickTimeout += getTickDelay();
        return 600;
    }

    // Get a randomized sleep delay
    public static long getSleepDelay() {
        sleepLength = getRandomDelay(sleepWeightedDistribution, sleepMin, sleepMax, sleepDeviation, sleepTarget);
        return sleepLength;
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
