package com.closenesscentrality.firstpart.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.closenesscentrality.bean.Edge;
import com.closenesscentrality.bean.Graph;
import com.closenesscentrality.bean.Node;
import com.closenesscentrality.core.GraphUtils;

public class Client {

	public static void main(String[] args) {
		
		try {

			FileInputStream file = new FileInputStream("classes/edges.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(file));	        
			
	        List<Edge> edges = new ArrayList<Edge>();
	        String inputLine;
			
	        while ((inputLine = br.readLine()) != null) {
	        
				String nodes[] = inputLine.split(" ");
	            int node1 = Integer.parseInt(nodes[0]);
	            int node2 = Integer.parseInt(nodes[1]);
	            edges.add(new Edge(node1, node2));
	        }
	        br.close();
			
			Graph graph = Graph.createGraph(100);
			GraphUtils graphUtils = new GraphUtils(graph);
			graph.addEdges(edges);
	        
			List<Node> sortedNodes = graphUtils.computeClosenessCentrality(graph);

			System.out.println("node index : closeness\n=========================");
			for (Node node : sortedNodes) {
				System.out.println(node.getIndex() + " : " + node.getCloseness());
			}
		} catch (IOException e) {
			System.out.println("Problem reading input file.");
			e.printStackTrace();
		}
	}
}
