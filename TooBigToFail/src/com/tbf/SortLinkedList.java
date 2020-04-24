package com.tbf;

import java.util.Comparator;

public class SortLinkedList<T> extends LinkedList<T> {
	private final Comparator<T> comparator;
	
	public SortLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
