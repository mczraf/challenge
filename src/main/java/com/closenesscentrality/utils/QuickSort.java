package com.closenesscentrality.utils;

import java.util.List;

import com.closenesscentrality.bean.Node;

public class QuickSort {

	public static void exchange (List<Node> a, int i, int j) {
		Node tmp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, tmp);
	}

	public static int partition(List<Node> a, int lo, int hi) {
		int i = lo; int j = hi+1;
		while(true) {
			while (a.get(++i).getCloseness() > a.get(lo).getCloseness())
				if (i == hi) break;
			while (a.get(--j).getCloseness() < a.get(lo).getCloseness())
				if (j == lo) break;
			if (i >= j) break;
			exchange(a, i, j);
		}
		exchange(a, lo, j);
		return j;
	}
	
	private static void sort(List<Node> a, int lo, int hi) {
		if (lo >= hi) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	
	public static void sort(List<Node> a, int size) {
		sort(a, 0, size-1);
	}
}
