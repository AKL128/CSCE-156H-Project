package com.tbf;
/*
*
*
*
*
*/
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

  private Node<T> head = null;
  private Node<T> tail = null;
  private int size;
  
  public LinkedList() {
	  this.head = null;
	  this.size = 0;
  }
  
  

  public void addElementToHead(T item) {
    if(item == null) {
      throw new IllegalArgumentException("Error: Null elements not allowed in this LinkedList");
    }
    Node<T> newHead = new Node<T>(item);
    if(this.tail == null) {
      this.head = newHead;
      this.tail = newHead;
    } else {
      newHead.setNext(this.head);
      this.head.setPrevious(newHead);
      this.head = newHead;
    }
  }

  public T removeElementFromHead() {
    T item = null;
    if(this.size() == 0) {
      throw new IllegalStateException("Error: Cannot remove from an empty list");
    } else if(this.size() == 1) {
      item = this.head.getItem();
      this.head = null;
      this.tail = null;
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
    Node<T> newTail = new Node<T>(item);
    if(this.tail == null) {
      this.head = newTail;
      this.tail = newTail;
    } else {
      newTail.setPrevious(this.tail);
      this.tail.setNext(newTail);
      this.tail = newTail;
    }
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
    String result = null;
    Node<T> curr = head;
    while(curr != null) {
      result += curr.getItem() + "\n";
      curr = curr.getNext();
    }
    return result;
  }
}
