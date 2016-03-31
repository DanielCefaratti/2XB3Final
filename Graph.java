package wt;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    private ArrayList<ArrayList<Integer>> adj;
    private int nV = 0;
    private int nE = 0;

    public Graph(){
        this.adj = new ArrayList<ArrayList<Integer>>();
    }

    public Graph(int nV){
        this.adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 1; i <= nV; ++i) {
            this.adj.add(new ArrayList<Integer>());
            this.nV ++;
        }
    }

    public void addEdge(int v, int w){
        // TODO: Add an edge from v to w (symmetrically).
    	if (v != w)
    	{
    		adj.get(v).add(w); // Add w to v’s list.
    		
    		// symmetrical add removed to make directed graph
        	// adj.get(w).add(v); // Add v to w’s list.
        	nE++;
    	}
    }

    public Iterable<Integer> adj(int v){
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

    public static void main(String[] args) throws IOException{
    	
        
        // Test ~20% total patent data (January 1, 1963 to December 30, 1999)
        Graph graph = new Graph(4551941);
        File f = new File("patentSlice.txt");
        
        // Full Patent data
//         Graph graph = new Graph(6009555);
//         File f = new File("patentDat.txt");
        
        
    	Scanner s = new Scanner(f);
    	
    	while(s.hasNextLine()){
			String connection = s.nextLine();
			String[] connections = {};
			if(!connection.isEmpty()){
				connections = connection.split("	");
			}
			graph.addEdge(Integer.parseInt(connections[1]), Integer.parseInt(connections[0]));
		}
        
        System.out.print(graph.toString());
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter patent nummber to check Score, or q to quit");
        
        while (true) {
            System.out.print("> ");
            String input = br.readLine();

            if (input.toLowerCase().equals("q")) {
               System.out.println("Exiting.");
               return;
            }

            
            System.out.println(Graphs.superDegree(graph, Integer.parseInt(input)));
         }
        
        

    }

}

