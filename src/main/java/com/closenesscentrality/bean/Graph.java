package com.closenesscentrality.bean;

import java.util.List;

/**
 * Represents a graph.
 * 
 * @author rafael
 *
 */
public class Graph {
	
	/* Maximum number of nodes: */
	private int nodes;
	/* Current number of edges: */
	private int edges;
	/* Array the represents a graph adjacency list: */
	private Node[] adj;
	/* Stores the distance between each of the nodes to all other nodes:*/
	private int[][] dist;
	/* Current instance of the graph:*/
	private static Graph instance;

	/**
	 * Returns the current instance.
	 * 
	 * @return current instance
	 */
	public static Graph getGraph() {
		return instance;
	}
	
	public static Graph createGraph(int maxNodes) {
		instance = new Graph(maxNodes);
		return instance;
	}
	/**
	 * Initializes the graph with 0 edges and the 
	 * maximum number of nodes.
	 * 
	 * @param maxNodes
	 */
	private Graph(int maxNodes) {
		this.edges = 0;
		this.nodes = maxNodes;
		this.adj = new Node[maxNodes];
		this.setDist(new int[maxNodes][maxNodes]);
		
		for (int i = 0; i < maxNodes; i++) 
			this.adj[i] = null;
	}

	public void addEdges(List<Edge> edges) {
		for (Edge e : edges) {
            addEdge(e.getNode1(), e.getNode2());
        }
	}
	
	private void addEdge(int indexNode1, int indexNode2) {
		for (Node a = adj[indexNode1]; a != null; a = a.getNext()) {
			if (a.getIndex() == indexNode2) {
				return;
			}
		}
		
		adj[indexNode1] = new Node(indexNode2, adj[indexNode1]);
		adj[indexNode2] = new Node(indexNode1, adj[indexNode2]);
		
		edges++;
	}
	
	public int getNodes() {
		return nodes;
	}

	public void setNodes(int nodes) {
		this.nodes = nodes;
	}

	public int getEdges() {
		return edges;
	}

	public void setEdges(int edges) {
		this.edges = edges;
	}

	public Node[] getAdj() {
		return adj;
	}

	public void setAdj(Node[] adj) {
		this.adj = adj;
	}

	public int[][] getDist() {
		return dist;
	}

	public void setDist(int[][] dist) {
		this.dist = dist;
	}
}
