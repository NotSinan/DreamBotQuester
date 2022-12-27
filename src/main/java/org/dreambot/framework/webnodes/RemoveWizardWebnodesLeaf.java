package org.dreambot.framework.webnodes;

import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.pathfinding.impl.web.WebFinder;
import org.dreambot.api.methods.walking.web.node.AbstractWebNode;
import org.dreambot.framework.Leaf;

public class RemoveWizardWebnodesLeaf extends Leaf {
    private boolean removed = false;
    private final Tile VARROCK_WIZARDS_CENTER_TILE = new Tile(3227, 3369, 0);
    @Override
    public boolean isValid() {
        return !removed;
    }

    @Override
    public int onLoop() {
        for (AbstractWebNode webNode : WebFinder.getWebFinder().getNodesWithin(12, VARROCK_WIZARDS_CENTER_TILE)) {
            WebFinder.getWebFinder().removeNode(webNode.getIndex());
        }
        removed = true;
        return 100;
    }
}
