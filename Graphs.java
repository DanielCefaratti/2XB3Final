package wt;

import java.util.*;

public class Graphs {
    public static int degree(Graph G, int v){
    	int degree = 0;
    	for (int w : G.adj(v)) degree++;
    	return degree;
    }
    
    public static int superDegree(Graph G, int v){
    	int degreeScore = 0;
    	for (int w : G.adj(v)){
    		degreeScore = degreeScore + 1 + superDegree(G, w);
    	}
    	return degreeScore;
    }

    public static int maxDegree(Graph G){
    	int max = 0;
    	for (int v = 0; v < G.V(); v++)
    	if (degree(G, v) > max)
    	max = degree(G, v);
    	return max;
    }

    public static int avgDegree(Graph G){
    	 return 2 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(Graph G){
    	int count = 0;
    	for (int v = 0; v < G.V(); v++)
    	for (int w : G.adj(v))
    	if (v == w) count++;
    	return count/2; 
    }

    public static Iterable DFS(Graph G, int src, int des){
        boolean[] visited = new boolean[G.V()];
        int[] edgeTo = new int[G.V()];
        for (int i = 0; i < edgeTo.length; ++i) edgeTo[i] = -1;

        dfs(G, src, visited, edgeTo);

        if (visited[des]){
            ArrayList<Integer> path = new ArrayList<Integer>();
            for (int i = des; i != src; i = edgeTo[i])
                path.add(i);
            path.add(src);
            Collections.reverse(path);
            return path;
        }else return null;
    }

    private static void dfs(Graph G, int src, boolean[] visited, int[] edgeTo){
    	visited[src] = true;
    	for (int w : G.adj(src))
    		if (!visited[w]){
    			edgeTo[w] = src;
    			dfs(G, w, visited, edgeTo);
    		}
    	
    }

    public static Iterable BFS(Graph G, int src, int des){
        boolean[] visited = new boolean[G.V()];
        int[] edgeTo = new int[G.V()];
        for (int i = 0; i < edgeTo.length; ++i) edgeTo[i] = -1;

        bfs(G, src, visited, edgeTo);

        if (visited[des]){
            ArrayList<Integer> path = new ArrayList<Integer>();
            for (int i = des; i != src; i = edgeTo[i])
                path.add(i);
            path.add(src);
            Collections.reverse(path);
            return path;
        }else return null;
    }

    private static void bfs(Graph G, int src, boolean[] visited, int[] edgeTo){
        // TODO: Verify the connectivity of the graph from src iteratively.

    }

}

