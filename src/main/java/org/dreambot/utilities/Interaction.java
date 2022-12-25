package org.dreambot.utilities;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.Entity;

public class Interaction {
    public static boolean delayEntityInteract(Entity entity, String action, long sleepDelay) {
        Sleep.sleep(sleepDelay);
        return entity.interact(action);
    }
    public static boolean delayEntityInteract(Entity entity, String action) {
        Sleep.sleep(Timing.getSleepDelay());
        return entity.interact(action);
    }
}
