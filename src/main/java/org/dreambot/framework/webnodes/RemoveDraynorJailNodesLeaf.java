package org.dreambot.framework.webnodes;

import org.dreambot.api.methods.walking.pathfinding.impl.web.WebFinder;
import org.dreambot.framework.Leaf;

public class RemoveDraynorJailNodesLeaf extends Leaf {
    private boolean removed = false;

    @Override
    public boolean isValid() {
        return !removed;
    }

    @Override
    public int onLoop() {
        WebFinder webFinder = WebFinder.getWebFinder();
        webFinder.removeNode(105);
        webFinder.removeNode(2943);
        webFinder.removeNode(106);
        webFinder.removeNode(2712);
        webFinder.removeNode(2713);
        webFinder.removeNode(2714);
        webFinder.removeNode(172);
        webFinder.removeNode(354);
        webFinder.removeNode(2959);
        webFinder.removeNode(2942);
        webFinder.removeNode(2944);
        webFinder.removeNode(2708);
        webFinder.removeNode(8791);
        webFinder.removeNode(2709);
        removed = true;
        return 100;
    }
}
