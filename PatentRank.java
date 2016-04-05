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
	private static String htmlOut = "";
	private static Graph graph;

	public static void main(String[] args) throws IOException {
		// Full Patent data
		graph = new Graph(numPatent);
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
		while (s.hasNextLine()) {
			String connection = s.nextLine();
			String[] connections = {};
			if (!connection.isEmpty()) {
				connections = connection.split("\\s+");
			}
			to = new Patent(0);
			from = new Patent(0);
			p1 = Integer.parseInt(connections[1]);
			p0 = Integer.parseInt(connections[0]);
			if (masterList.get(p1).isEmpty())// if the index contains no node
			{
				to = new Patent(p1);// make a new node
				masterList.set(p1, to);// add to master list
				topLevel.set(p1, to);// add to top level list
			} else {
				to = masterList.get(p1);
			}
			if (masterList.get(p0).isEmpty())// if masterlist does not contain
												// node
			{
				from = new Patent(p0);// make new node
				masterList.set(p0, from);// add node to master list
			} else {
				from = masterList.get(p0);// set from
				topLevel.set(p0, empty);
			}
			// System.out.println(p0);
			graph.addEdge(from, to);
			// System.out.println(tempInt);
			tempInt++;

		}
		changeTop();
		dfs = new DepthPathing(graph, user);
		System.out.println("done");
		System.out.println(masterList.size());
		System.out.println(topLevel.size());
		
//		// User input loop
//		while (true) {
//			System.out.print("> ");
//			String input = br.readLine();
//
//			// Exit condition
//			if (input.toLowerCase().equals("q")) {
//				System.out.println("Exiting.");
//				return;
//			}
//			
//			// Parse input
//			int query = 0;
//			try{
//				query = Integer.parseInt(input);
//			}
//			catch(Exception e){
//				System.out.println("Invalid input");
//				return;
//			}
//			
//			// Confirm presence of patent in dataset
//			if (masterList.get(query).getId() == -1) {
//				System.out.println("Patent not in dataset");
//				return;
//			}
//			
//			// Top patent identification
//			int topId = topPat(graph, masterList.get(query)).getId();
//			if (topId == query) {
//				System.out.println("Top: This is the top patent");
//			} else {
//				System.out.println("Top: " + topId);
//			}
//
//			// Patent score
//			System.out.print("Your Score: ");
//			int score = Graphs.superDegree(graph, masterList.get(query));
//			System.out.println(score);
//			htmlOut += "<tr bgcolor=\"#80ffaa\"><td><b>" + score + "</b></td><td><b>" + WebGetter.getPatName(query) + "</b></td></tr>";
//			
//			// Bottom patent identification
//			int bottomId = botPat(graph, masterList.get(query)).getId();
//			if (bottomId == -1) {
//				System.out.println("Bottom: This is the bottom patent");
//			} else {
//				System.out.println("Bottom: " + bottomId);
//			}
//			
//			// Launch html browser
//			htmlOut = "<table><tr><td><h3>Top</h3></td></tr>" + htmlOut + "<tr><td><h3>Bottom</h3></td></tr></table>";
//			System.out.println(htmlOut);
//			new ShowBrowser().start(htmlOut);
//		}
		
		startRank();
	}

	public static void changeTop() {
		ArrayList<Patent> list = new ArrayList<Patent>();
		for (int i = 1; i < topLevel.size(); i++) {
			// System.out.println(i);
			if (topLevel.get(i).isEmpty() == false) {
				list.add(topLevel.get(i));
			}
		}
		topLevel = list;
	}

	public static void initPatents(ArrayList<Patent> list, int numPatent) {
		Patent temp = new Patent(-1);
		for (int i = 0; i <= numPatent; i++) {
			list.add(temp);
		}
	}

	// Top patent by Depth Pathing
	public static Patent topPat(DepthPathing dp, Graph G) {
		Patent top = new Patent(0);
		int max = 0;
		for (Patent n : topLevel) {
			System.out.println("Hi");
			if (dp.hasPathTo(n.getId()) != false) {

				int temp = Graphs.superDegree(G, n);
				if (temp > max) {
					top = n;
					max = temp;
				}
			}

		}
		return top;
	}

	// Top patent by recursion
	public static Patent topPat(Graph G, Patent top) throws IOException {
		int maxDegree = 0;
		if (!top.getAbove().isEmpty()) {
			for (Patent n : top.getAbove()) {
				if (Graphs.superDegree(G, n) >= maxDegree) {
					maxDegree = Graphs.superDegree(G, n);
					top = n;
				}
			}
			htmlOut = "<tr><td>" + Graphs.superDegree(G, top) + "</td><td>" + WebGetter.getPatName(top.getId()) + "</td></tr>" + htmlOut;
			return topPat(G, top);
		} else {
			return top;
		}
	}
	
	public static Patent botPat(Graph g, Patent bottom) throws IOException {
		int maxDegree = 0;		
		if (!bottom.getBelow().isEmpty()) {
			for (Patent n : bottom.getBelow()) {
				if (Graphs.superDegree(g, n) >= maxDegree) {
					maxDegree = Graphs.superDegree(g, n);
					bottom = n;
				}
			}
			htmlOut += "<tr><td>" + Graphs.superDegree(g, bottom) + "</td><td>" + WebGetter.getPatName(bottom.getId()) + "</td></tr>";
			return botPat(g, bottom);
		} else {
			return bottom;
		}

	}
	
	public static void startRank() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter patent nummber to check Score, or q to quit");
		
		System.out.print("> ");
		String input = br.readLine();

		// Exit condition
		if (input.toLowerCase().equals("q")) {
			System.out.println("Exiting.");
			return;
		}
		
		// Parse input
		int query = 0;
		try{
			query = Integer.parseInt(input);
		}
		catch(Exception e){
			System.out.println("Invalid input");
			startRank();
		}
		
		// Confirm presence of patent in dataset
		if (masterList.get(query).getId() == -1) {
			System.out.println("Patent not in dataset");
			startRank();
		}
		
		// Top patent identification
		int topId = topPat(graph, masterList.get(query)).getId();
		if (topId == query) {
			System.out.println("Top: This is the top patent");
		} else {
			System.out.println("Top: " + topId);
		}

		// Patent score
		System.out.print("Your Score: ");
		int score = Graphs.superDegree(graph, masterList.get(query));
		System.out.println(score);
		htmlOut += "<tr bgcolor=\"#80ffaa\"><td><b>" + score + "</b></td><td><b>" + WebGetter.getPatName(query) + "</b></td></tr>";
		
		// Bottom patent identification
		int bottomId = botPat(graph, masterList.get(query)).getId();
		if (bottomId == -1) {
			System.out.println("Bottom: This is the bottom patent");
		} else {
			System.out.println("Bottom: " + bottomId);
		}
		
		// Launch html browser
		htmlOut = "<table><tr><td><h3>Top</h3></td></tr>" + htmlOut + "<tr><td><h3>Bottom</h3></td></tr></table>";
		System.out.println(htmlOut);
		new ShowBrowser().start(htmlOut);
		startRank();
	}
}
