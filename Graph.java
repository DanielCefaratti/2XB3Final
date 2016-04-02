package wt;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
	
    private ArrayList<ArrayList<Patent>> adj;
  
    
    private int nV = 0;
    private int nE = 0;

    public Graph(){
        this.adj = new ArrayList<ArrayList<Patent>>();
    }

    public Graph(int nV){
        this.adj = new ArrayList<ArrayList<Patent>>();
        for (int i = 1; i <= nV; ++i) {
            this.adj.add(new ArrayList<Patent>());
            this.nV ++;
        }
    }

    public void addEdge(Patent v, Patent w){
    		adj.get(v.getId()).add(w); // Add w to v’s list.
    		v.addBelow(w);
    		w.addAbove(v);
        	nE++;
    }

    public Iterable<Patent> adj(int v){
        return adj.get(v);
    }

    public int V(){
        return nV;
    }

    public int E(){
        return nE;
    }


    public String toString(){
        String s = this.V() + " vertices, " + this.E() + " edges\n";
//        for (int v = 0; v < this.V(); ++v){
//            s += v + ": ";
//            for (int w : this.adj(v))
//                s += w + " ";
//            s += "\n";
//        }
        return s;
    }

    

}

