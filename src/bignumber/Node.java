package bignumber;

/**
 * The bignumber.Node class represents an object template which is used to store the digit of a
 * bignumber.BigNumber This is node template in the linkedList implementation.
 */
public class Node {
  int data;
  Node next;
  Node prev;

  public Node() {
    next = null;
    prev = null;
  }

  /** created node with value and set both pointers to null. */
  public Node(int t) {
    this.data = t;
    this.next = null;
    this.prev = null;
  }

  /** created node with value and set both pointers. */
  public Node(int data, Node next, Node prev) {
    this.data = data;
    this.next = next;
    this.prev = prev;
  }
}
