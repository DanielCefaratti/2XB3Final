package wt;

import java.util.Stack;

public class DepthPathing {
	private boolean[] marked;    // marked[v] = is there an s-v path?
    private Patent[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final Patent s;         // source vertex
    private Patent[] edgeUp;
    private boolean[] markedUp;

    public DepthPathing(Graph G, Patent s) {
        this.s = s;
        edgeTo = new Patent[G.V()];
        marked = new boolean[G.V()];
        edgeUp = new Patent[G.V()];
        markedUp = new boolean[G.V()];
        dfs(G, s);
        dfsUp(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, Patent v) {
        marked[v.getId()] = true;
        for (Patent w : G.adj(v.getId())) {
            if (!marked[w.getId()]) {
                edgeTo[w.getId()] = v;
                dfs(G, w);
            }
        }
    }
    
    private void dfsUp(Graph G, Patent v) {
        markedUp[v.getId()] = true;
        for (Patent w : G.adjUp(v.getId())) {
            if (!markedUp[w.getId()]) {
                edgeUp[w.getId()] = v;
                dfsUp(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return markedUp[v];
    }

    public Iterable<Patent> pathTo(Patent v) {
        if (!hasPathTo(v.getId())) return null;
        Stack<Patent> path = new Stack<Patent>();
        for (Patent x = v; x != s; x = edgeUp[x.getId()])
            path.push(x);
        path.push(s);
        return path;
    }

}
