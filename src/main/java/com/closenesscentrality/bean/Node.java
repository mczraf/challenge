package com.closenesscentrality.bean;

/**
 * Represents a node of a graph.
 * 
 * @author rafael
 *
 */
public class Node {

	/* Naming for the node: */
	private int index;
	/* Next node (useful for graph adjacency array):*/
	private Node next;
	/* Closeness value for this node (useful for computing centrality): */
	private double closeness;

	public Node() {
		
	}
	
	public Node(int index, double closeness) {
		this.index = index;
		this.closeness = closeness;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public double getCloseness() {
		return closeness;
	}

	public void setCloseness(double closeness) {
		this.closeness = closeness;
	}

	public Node(int index, Node next) {
		this.index = index;
		this.next = next;
	}
}
