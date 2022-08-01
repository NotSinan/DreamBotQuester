# DreamBot-Delayed-TBL

An augmented Tree-Branch-Leaf framework that also includes tick delays and delayed interacts. Adapted from [Lost-Delayed-TBL-Framework](https://github.com/Bonfire/Lost-Delayed-TBL-Framework) for DreamBot.

Notes:

-   To utilize tick delays, be sure that you always return `API.loopReturn()` in your leaves
-   To utilize delayed interacts, follow the example code I've written under the `Interaction.java` class. You must write a wrapper around your interact code and supply delays.

## Setup

1. Download the code (I prefer as a ZIP file)
2. Extract the `src/` folder to your script module
3. Begin writing your tree

## Structure

-   `src/org/dreambot/behaviour` - This is where your branches and leaves should be defined. They describe the functionality of your script. I'd recommend not touching the `tick` functionality unless you know what you are doing.
    -   `timeout` - This behavior branch is called anytime there is a "tick timeout" (`Timing.tickTimeout > 0`). It will take priority over all other branches/leaves, decrement the `tickTimeout` by one, then wait 600ms (one tick).
    -   `fallback` - This is the "fallback" branch, to prevent tick timeout deadlocks. This behavior branch is only called if there is a tick timeout (`Timing.tickTimeout > 0`) and there are no other valid leaves. It will reset the tick timeout to a random value using `Timing.loopReturn()`.
    -   `combat` - An example behavior branch that shows how tick timeouts and sleep delays can be used. For actions that requires little to no delay between ticks the current tick timeout can be set to a small value (`Timing.tickTimeout = 1`). Then have the leaf return immediately (`return 0;`)
-   `src/org/dreambot/framework` - This contains the core of the Tree-Branch framework code. Credits to LostVirt.
-   `src/org/dreambot/paint` - Where the core of the script paint code is defined. Credits to Fluffy.
-   `src/org/dreambot/utilities` - Contains our utility classes (and it's where you should define any future utility classes).
    -   `API.java` - Holds current tree information.
    -   `Interaction.java` - An example interaction helper that utilizes the framework's sleep delay system. Do note that some interactions are handled by the client (such as walking a path), so only the first action will have a delay.
    -   `Timing.java` - Sleep delay and tick delay settings. Modify the settings to your liking. Also contains the method used in deriving both delays.
-   `src/org/dreambot/Main.java` - Where you change your script's manifest, branches/leaves, and paint.

## Credits

-   [LostVirt's Dreambot-Tree-Branch-Framework](https://github.com/LostVirt/Dreambot-Tree-Branch-Framework) - For the bulk of framework code here
-   [Elli-tt's elutils](https://github.com/Elli-tt/el-plugins-source) - For the tick and sleep delay ideas and code
