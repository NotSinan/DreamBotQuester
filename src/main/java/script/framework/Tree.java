package script.framework;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LostVirt
 * @created 05/10/2021 - 21:54
 */
public class Tree
{
    private final Root root;

    public Tree()
    {
        root = new Root();
    }

    public Leaf addBranches(Leaf... leaves)
    {
        root.addLeafs(leaves);
        return root;
    }

    public void clear()
    {
        root.children.clear();
    }

    public List<String> getActiveBranches()
    {
        return root.children.stream().map(tLeaf -> tLeaf.getClass().getSimpleName()).collect(Collectors.toList());
    }

    public int onLoop()
    {
        return root.onLoop();
    }
}
