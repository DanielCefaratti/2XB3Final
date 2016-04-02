package wt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PatentRank {
	private static int numPatent = 4551941;
	private static ArrayList<Patent> masterList = new ArrayList<Patent>(numPatent);
	private static ArrayList<Patent> topLevel = new ArrayList<Patent>();
	
public static void main(String[] args) throws IOException{
    	
        
        // Test ~20% total patent data (January 1, 1963 to December 30, 1999)
        Graph graph = new Graph(4551941);
        File f = new File("patentSlice.txt");
        
        // Full Patent data
//         Graph graph = new Graph(6009555);
//         File f = new File("patentDat.txt");
        
        
    	Scanner s = new Scanner(f);
    	int index = 0;
    	while(s.hasNextLine()){
			String connection = s.nextLine();
			String[] connections = {};
			if(!connection.isEmpty()){
				connections = connection.split("//s*");
			}
			int p1 = Integer.parseInt(connections[1]);
			int p0 = Integer.parseInt(connections[0]);
			Patent to;
			Patent from;
			if (masterList.get(p1) == null)//if the index contains no node
			{
				to = new Patent(p1);//make a new node
				masterList.set(p1, to);//add to master list
				topLevel.add(to);//add to top level list
			}
			else
			{
				to = masterList.get(p1);
			}
			if (masterList.get(p0) == null)//if masterlist does not contain node
			{
				from = new Patent(p0);//make new node
				masterList.set(p0, from);//add node to master list
			}
			else
			{
				from = masterList.get(p0);//set from
				if (topLevel.contains(from))//remove from in the top level list if it exists
				{
					topLevel.remove(from);
				}
			}
			
			graph.addEdge(from, to);
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

            
            //System.out.println(Graphs.superDegree(graph, Integer.parseInt(input)));
         }
        
        

    }

}
