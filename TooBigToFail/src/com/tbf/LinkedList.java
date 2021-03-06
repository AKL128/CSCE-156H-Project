package com.tbf;
import java.util.Comparator;
/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/4/20
 *
 * This is the ADT Linked List class that is used to handle our data 
 *
 */
import java.util.Iterator;



public class LinkedList<T> implements Iterable<T> {
	
	private Comparator<T> comparator;

	private Node<T> head = null;
	private Node<T> tail = null;
	private int size;
  
  public LinkedList(Comparator<T> comparator) {
	  this.comparator = comparator;
	  this.head = null;
	  this.size = 0;
	  
  }
  

  public void addElementBySort(T item) {
	  if(item == null) {
	      throw new IllegalArgumentException("Error: Null elements not allowed in this LinkedList");
	    }
	  
	  if(this.isEmpty()) {
		  addElementToHead(item);
	  } else {
		  int beg = 0;
		  int rear = this.size();
		  while(beg < rear && this.comparator.compare(item, this.getElementAtIndex(beg)) > 0) {
			  beg++;
		  }
		  this.size++;
		  this.insertAtIndex(item, beg);
	  }
  }
  
  private Node<T> getNodeAtIndex(int index) {
	  if(index < 0 || index >= this.size) {
		  throw new IllegalArgumentException("Index out of bounds!");
	  }
	  
	  Node<T> curr = this.head;
	  for(int i = 0; i < index; i++) {
		  curr = curr.getNext();
	  }
	  return curr;
  }
  
  public T getElementAtIndex(int index) {
	  return getNodeAtIndex(index).getItem();
  }
  
  public void insertAtIndex(T item, int index) {
	  if(index < 0 || index > this.size) {
		  throw new IllegalArgumentException("index out of bounds!");
	  }
	  if(index == 0) {
		  this.addElementToHead(item);
	  } else if(index == this.size) {
		  this.addElementToTail(item);
	  } else {
		  Node<T> newNode = new Node(item);
		  Node<T> prevNode = this.getNodeAtIndex(index-1);
		  Node<T> currNode = this.getNodeAtIndex(index);
		  newNode.setNext(currNode);
		  prevNode.setNext(newNode);
		  this.size++;
	  }
  }
  
  

  public void addElementToHead(T item) {
    if(item == null) {
      throw new IllegalArgumentException("Error: Null elements not allowed in this LinkedList");
    }
    Node<T> newHead = new Node<>(item);
    newHead.setNext(this.head);
    this.head = newHead;
    this.size++;
  }

  public T removeElementFromHead() {
    T item = null;
    if(this.size() == 0) {
      throw new IllegalStateException("Error: Cannot remove from an empty list");
    } else if(this.size() == 1) {
      item = this.head.getItem();
      this.head = null;
      this.tail = null;
      this.size--;
    } else {
      item = this.head.getItem();
      this.head = this.head.getNext();
      this.head.setPrevious(null);
    }
    return item;
  }

  public T getElementFromHead() {
    if(this.size() == 0) {
			throw new IllegalStateException("Error: Cannot retrieve from an empty list");
		} else {
			return this.head.getItem();
		}
  }

  public void addElementToTail(T item) {
    if(item == null) {
      throw new IllegalArgumentException("Error: Error: Null elements not allowed in this LinkedList");
    }
    
    if(this.isEmpty()) {
    	addElementToHead(item);
    	return;
    }
    Node<T> curr = this.head;
    while(curr.getNext() != null) {
    	curr = curr.getNext();
    }
    Node<T> newTail = new Node<T>(item);
    curr.setNext(newTail);
    this.size++;
  }

  public T removeElementFromTail() {
    T item = null;
    if(this.size() == 0) {
      throw new IllegalStateException("Error: Cannot remove from empty list");
    } else if(this.size() == 1) {
      item = this.tail.getItem();
      this.head = null;
      this.tail = null;
    } else {
      item = this.tail.getItem();
      this.tail = this.tail.getPrevious();
      this.tail.setNext(null);
    }
    return item;
  }

  public T getElementFromTail() {
    if(this.size() == 0) {
			throw new IllegalStateException("Error: Cannot retrieve from an empty list");
		} else {
			return this.tail.getItem();
		}
  }

  public int size() {
    int count = 0;
    for(T item : this) {
      count++;
    }
    return count;
  }

  public boolean isEmpty() {
    return (head == null);
  }
  
  public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> curr = head;
			@Override
			public boolean hasNext() {
				if(curr == null)
					return false;
				else
					return true;
			}
			@Override
			public T next() {
				T item = curr.getItem();
				curr = curr.getNext();
				return item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("not implemented");
			}};
	}

  public String toString() {
		if(this.head == null) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> curr = head;
		while(curr != null) {
			sb.append(curr.getItem());
			if(curr.getNext() != null)
				sb.append(", ");
			curr = curr.getNext();
		}
		sb.append("]");
		return sb.toString();
	}
 
}
