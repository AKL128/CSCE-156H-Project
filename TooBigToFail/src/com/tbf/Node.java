package com.tbf;
/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/4/20
 *
 * This is a Node class that is part of the ADT implementation of Linked List class
 *
 */

public class Node<T> {

  private final T item;
  private Node<T> prev;
  private Node<T> next;

  public Node(T item) {
    this.item = item;
    next = null;
  }

  public T getItem() {
    return this.item;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public void setPrevious(Node<T> prev) {
    this.prev = prev;
  }

  public Node<T> getNext() {
    return this.next;
  }

  public Node<T> getPrevious() {
    return this.prev;
  }
}
