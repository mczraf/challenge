package com.closenesscentrality.utils;


/**
 * Implements the queue data-structure.
 * 
 * @author rafael
 *
 */
public class Queue {

	private static int[] fila;
	private static int ini;
	private static int fim;
	private static Queue instance;

	private Queue(int size) {
		fila = new int[size];
		ini = fim = 0;
	}
	
	/**
	 * Singleton manager.
	 * 
	 * @return current instance
	 */
	public static Queue getQueue(int size) {
		if (instance == null) {
			instance = new Queue(size);
		}
		return instance;
	}
	
	public boolean isEmpty() {
		return ini == fim;
	}
	
	public void enqueue(Integer vertex) {
		fila[fim++] = vertex;
	}
	
	public Integer dequeue() {
		return fila[ini++];
	}

	public void free( ) {
	   instance = null;
	}
}
