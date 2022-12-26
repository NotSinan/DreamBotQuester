package org.dreambot.framework.webnodes;

import org.dreambot.api.methods.walking.pathfinding.impl.web.WebFinder;
import org.dreambot.framework.Leaf;

public class RemoveWizardWebnodesLeaf extends Leaf {
    private boolean removed = false;

    @Override
    public boolean isValid() {
        return !removed;
    }

    @Override
    public int onLoop() {
        WebFinder webFinder = WebFinder.getWebFinder();
        webFinder.removeNode(2812);
        webFinder.removeNode(2813);
        webFinder.removeNode(8797);
        webFinder.removeNode(2810);
        webFinder.removeNode(2811);
        webFinder.removeNode(66);
        removed = true;
        return 100;
    }
}
