package org.dreambot.framework;

import java.util.List;
import java.util.stream.Collectors;

public class Tree {
  private final Root root;

  public Tree() {
    root = new Root();
  }

  public Leaf addBranches(Leaf... leaves) {
    root.addLeafs(leaves);
    return root;
  }

  public void clear() {
    root.children.clear();
  }

  public List<String> getActiveBranches() {
    return root.children.stream()
        .map(tLeaf -> tLeaf.getClass().getSimpleName())
        .collect(Collectors.toList());
  }

  public int onLoop() {
    return root.onLoop();
  }
}
