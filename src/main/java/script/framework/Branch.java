package script.framework;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.dreambot.api.utilities.Sleep;

import script.utilities.API;

public abstract class Branch extends Leaf
{

    public final List<Leaf> children;

    public Branch()
    {
        this.children = new LinkedList<>();
    }


    public Branch addLeafs(Leaf... leaves)
    {
        Collections.addAll(this.children, leaves);
        return this;
    }


    @Override
    public int onLoop()
    {
        return children.stream()
                .filter(c -> Objects.nonNull(c) && c.isValid())
                .findAny()
                .map(tLeaf -> {
                    API.currentBranch = this.getClass().getSimpleName();
                    API.currentLeaf = tLeaf.getClass().getSimpleName();
                    return tLeaf.onLoop();
                }).orElseGet(() -> {
                    Sleep.sleep(25,50);
                    return 0;
                });
    }
}
