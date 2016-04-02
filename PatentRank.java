package wt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PatentRank {

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
			Patent temp1 = new Patent(Integer.parseInt(connections[1]));
			Patent temp2 = new Patent(Integer.parseInt(connections[0]));
			graph.addEdge(temp1, temp2);
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
