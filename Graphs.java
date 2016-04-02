package wt;

import java.util.*;

public class Graphs {
    public static int degree(Graph G, Patent v){
    	int degree = 0;
    	for (Patent w : G.adj(v.getId())) degree++;
    	return degree;
    }
    
    public static int superDegree(Graph G, Patent v){
    	int degreeScore = 0;
    	for (Patent w : G.adj(v.getId())){
    		degreeScore = degreeScore + 1 + superDegree(G, w);
    	}
    	return degreeScore;
    }
    /*
    public static int maxDegree(Graph G){
    	int max = 0;
    	for (Patent w: )
    	if (degree(G, v) > max)
    	max = degree(G, v);
    	return max;
    }*/

    public static int avgDegree(Graph G){
    	 return 2 * G.E() / G.V();
    }
    /*
    public static int numberOfSelfLoops(Graph G){
    	int count = 0;
    	for (int v = 0; v < G.V(); v++)
    	for (int w : G.adj(v))
    	if (v == w) count++;
    	return count/2; 
    }*/
  

}

