package org.dreambot.framework.webnodes;

import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.pathfinding.impl.web.WebFinder;
import org.dreambot.api.methods.walking.web.node.AbstractWebNode;
import org.dreambot.framework.Leaf;

public class RemoveDraynorJailNodesLeaf extends Leaf {
  private boolean removed = false;
  private final Tile DRAYNOR_JAIL_CENTER_TILE = new Tile(3121, 3247, 0);

  @Override
  public boolean isValid() {
    return !removed;
  }

  @Override
  public int onLoop() {
    for (AbstractWebNode webNode :
        WebFinder.getWebFinder().getNodesWithin(17, DRAYNOR_JAIL_CENTER_TILE)) {
      WebFinder.getWebFinder().removeNode(webNode.getIndex());
    }
    removed = true;
    return 100;
  }
}
