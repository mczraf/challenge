package com.closenesscentrality.core;

import java.util.ArrayList;
import java.util.List;

import com.closenesscentrality.bean.Graph;
import com.closenesscentrality.bean.Node;
import com.closenesscentrality.utils.Queue;
import com.closenesscentrality.utils.QuickSort;


public class GraphUtils {

	private Graph graph;
	
	public GraphUtils(Graph graph) {
		this.graph = graph;
	}
	
	private void computeDistance(Graph G, Integer s) 
	{ 
	   int INFINITY = G.getNodes();
	   int v;
	   
	   for (v = 0; v < G.getNodes(); v++)
	      graph.getDist()[s][v] = INFINITY;
	   
	   Queue queue = Queue.getQueue(G.getNodes());
	   graph.getDist()[s][s] = 0;
	   queue.enqueue(s); 

	   while (!queue.isEmpty()) {
		   
	      v = queue.dequeue(); 
	     
		  for (Node a = G.getAdj()[v]; a != null; a = a.getNext()) {
	         if (graph.getDist()[s][a.getIndex()] == INFINITY) { 
	        	 graph.getDist()[s][a.getIndex()] = graph.getDist()[s][v] + 1;
	            queue.enqueue(a.getIndex()); 
	         }
	      }
	   }
	   queue.free();
	}
	
	public List<Node> computeClosenessCentrality(Graph graph) {

		int INFINITY = graph.getNodes();
		Double closeness[] = new Double[graph.getNodes()];
        List<Node> verticesSortedByCloseness = new ArrayList<Node>();
		
        int i, j;
    	for (i = 0; i < graph.getNodes(); i++) 
    		computeDistance(graph, i); 
		
    	for (j = 0; j < graph.getNodes(); j++) {
    		int f = 0;
    	  	for (i = 0; i < graph.getNodes(); i++) {
    	  		if (graph.getDist()[j][i] != INFINITY)
    	  			f = f + graph.getDist()[j][i];
    	  	}
    	  	
    	  	if (f != 0)
    	  		closeness[j] = 1.0/f;
    	  	else
    	  		closeness[j] = 0.0;
    			
    		verticesSortedByCloseness.add(new Node(j, closeness[j]));
    	}
    	
    	QuickSort.sort(verticesSortedByCloseness, graph.getNodes());
    	
		return verticesSortedByCloseness;
	}
	
}
