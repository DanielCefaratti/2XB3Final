package wt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PatentRank {
	private static int numPatent = 6009555;
	private static ArrayList<Patent> masterList = new ArrayList<Patent>();
	private static ArrayList<Patent> topLevel = new ArrayList<Patent>();
	private static DepthPathing dfs;
	private static Patent user = new Patent(0);
	
public static void main(String[] args) throws IOException{
        // Full Patent data
		Graph graph = new Graph(numPatent);
		File f = new File("patentDat.txt");
        
        
    	Scanner s = new Scanner(f);
    	int index = 0;
    	int tempInt = 0;
    	Patent to = new Patent(0);
		Patent from = new Patent(0);
		Patent empty = new Patent(-1);
		int p1;
		int p0;
    	initPatents(masterList, numPatent);
    	initPatents(topLevel, numPatent);
    	while(s.hasNextLine()){
			String connection = s.nextLine();
			String[] connections = {};
			if(!connection.isEmpty()){
				connections = connection.split("\\s+");
			}
			to = new Patent(0);
			from = new Patent(0);
			p1 = Integer.parseInt(connections[1]);
			p0 = Integer.parseInt(connections[0]);
			if (masterList.get(p1).isEmpty())//if the index contains no node
			{
				to = new Patent(p1);//make a new node
				masterList.set(p1, to);//add to master list
				topLevel.set(p1, to);//add to top level list
			}
			else
			{
				to = masterList.get(p1);
			}
			if (masterList.get(p0).isEmpty())//if masterlist does not contain node
			{
				from = new Patent(p0);//make new node
				masterList.set(p0, from);//add node to master list
			}
		 	else
			{
				from = masterList.get(p0);//set from
				topLevel.set(p0,empty);
			}
			//System.out.println(p0);
			graph.addEdge(from, to);
			//System.out.println(tempInt);
			tempInt++;
			
			
		}
    	changeTop();
        dfs = new DepthPathing(graph, user);
        System.out.println("done");
        System.out.println(masterList.size());
        System.out.println(topLevel.size());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter patent nummber to check Score, or q to quit");
        
        while (true) {
            System.out.print("> ");
            String input = br.readLine();

            if (input.toLowerCase().equals("q")) {
               System.out.println("Exiting.");
               return;
            }

            
            System.out.println(Graphs.superDegree(graph, masterList.get(Integer.parseInt(input))));
         }
    }

	public static void changeTop()
	{
		ArrayList<Patent> list = new ArrayList<Patent>();
		for(int i = 1; i< topLevel.size(); i++)
		{
			//System.out.println(i);
			if (topLevel.get(i).isEmpty() == false)
			{
				list.add(topLevel.get(i));
			}
		}
		topLevel = list;
	}
	public static void initPatents(ArrayList<Patent> list, int numPatent)
	{
		Patent temp = new Patent(-1);
		for(int i=0; i<= numPatent; i++)
		{
			list.add(temp);
		}
	}
	//get the top most patent
	public Patent topPat(DepthPathing dp)
	{
		Patent top = new Patent(0);
		int index = 0;
		for (Patent n:topLevel)
		{
			int index1 = 0;
			for (Patent v:dp.pathTo(n))
			{
				index1++;
			}
			if (index1 > index)
			{
				index = index1;
				top = n;
			}
		}
		return top;
	}
	//get the bottom most patent recursively
	public Patent botPat(Graph g, Patent user)
	{
		Patent bottom = new Patent(0);
		int maxDegree = 0;
		if (!user.getBelow().isEmpty())
		{
			for (Patent n: user.getBelow())
			{
				if (Graphs.superDegree(g, n) > maxDegree)
				{
					bottom = n;
					maxDegree = Graphs.superDegree(g, n);
				}
			}
			return botPat(g, bottom);
		}
		else
		{
			return bottom;
		}
			
	}
}
