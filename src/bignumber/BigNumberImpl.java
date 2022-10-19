package bignumber;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Implementation class of the bignumber.BigNumber interface. */
public class BigNumberImpl implements Iterable<Integer>, BigNumber, Comparable<BigNumber> {
  private Node head;
  private Node tail;
  private int size;
  private boolean isPositive;

  /** constructor with no parameters that initializes this number to 0. */
  public BigNumberImpl() {
    linkedList();
    addLast(0);
    this.isPositive = true;
  }

  /**
   * constructor that takes a number as a string and represents it. Throws an
   * IllegalArgumentException if the string passed to it does not represent a valid number.
   */
  public BigNumberImpl(String s) {
    linkedList();
    int strLength = s.length();
    if (strLength <= 0) {
      throw new IllegalArgumentException("The input should not be empty");
    }
    char zero = '0';
    size = 0;
    isPositive = true;

    if (strLength == 1) {
      char c = s.charAt(0);
      if (c < '0' || c > '9') {
        throw new IllegalArgumentException("The string should contains only numbers");
      }
      addFirst((c - zero));
      return;
    }
    if (strLength == 2 && s.charAt(0) == '-' && s.charAt(1) == '0') {
      isPositive = true;
      addLast(0);
      return;
    }
    if (s.charAt(0) == '-') {
      isPositive = false;
    }
    int beginning = (isPositive) ? 0 : 1;
    for (int i = beginning; i < strLength; i++) {
      char c = s.charAt(i);
      if (c < '0' || c > '9') {
        throw new IllegalArgumentException("The string should contains only numbers");
      }
      if (size == 0 && c == '0') {
        continue;
      }
      addLast((c - zero));
    }
  }

  public void setHead(Node head) {
    this.head = head;
  }

  public void setTail(Node tail) {
    this.tail = tail;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void setPositive(boolean positive) {
    isPositive = positive;
  }

  /**
   * toString method that returns a string representation of this number, as simply the number
   * itself.
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "";
    }
    StringBuilder out = new StringBuilder("");
    if (!isPositive) {
      out.append("-");
    }
    Iterator it = iterator();
    while (it.hasNext()) {
      String val = String.valueOf(it.next());
      out.append(val);
    }
    return out.toString();
  }

  public void linkedList() {
    head = tail = null;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  /** add element at the head of the linked list. */
  public void addFirst(int element) {
    Node temp = new Node(element);

    if (isEmpty()) {
      head = tail = temp;
    } else {
      temp.next = head;
      head.prev = temp;
      head = temp;
    }
    size++;
  }

  /** add element at the tail of the linked list. */
  public void addLast(int element) {
    Node temp = new Node(element);
    if (head == null) {
      head = tail = temp;
    } else {
      temp.prev = tail;
      tail.next = temp;
      tail = temp;
    }
    size++;
  }


  /** remove first node from linked list. */
  public int removeFirst() {
    if (size == 0) {
      throw new UnsupportedOperationException("The list was already empty");
    }
    Node newHead = head.next;
    int oldElement = head.data;
    head = newHead;
    size--;
    return oldElement;
  }

  /** remove from tail. */
  public int removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Oops! Cannot remove item from an empty Queue.");
    }
    final int item = tail.data;
    Node tmp = tail.prev;
    if (tmp != null) {
      tmp.next = null;
    }
    if (tmp == null) {
      head = null;
    }
    tail = tmp;
    size--;
    if (isEmpty()) {
      head = null;
      tail = null;
    }
    return item;
  }

  /** return element at specified index in linked list. */
  public int valueAtIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("index out of bounds");
    }
    if (index == 0) {
      return head.data;
    }
    if (index == size - 1) {
      return tail.data;
    }
    Node current = head;
    for (int i = 1; i <= index; i++) {
      current = current.next;
    }
    return current.data;
  }

  @Override
  public Iterator iterator() {
    return new LinkedIterator(head);
  }

  public Iterator reversedIterator() {
    return new ReversedLinkedIterator(tail);
  }

  /** method to compare two BigNumber objects. */
  @Override
  public int compareTo(BigNumber o) {
    BigNumberImpl other = (BigNumberImpl) o.copy();
    int sign1 = isPositive ? 1 : 0;
    int sign2 = other.isPositive ? 1 : 0;

    // Compare positive with a negative number
    if ((sign1 ^ sign2) == 1) {
      if (sign1 == 1 && sign2 == 0) {
        return 1;
      } else {
        return -1;
      }
    }

    // signs are the same
    int size1 = size;
    int size2 = other.size;

    if ((size1 ^ size2) > 0) {
      if (size1 > size2) {
        return sign1 == 1 ? 1 : -1;
      } else {
        return sign1 == 0 ? 1 : -1;
      }
    }
    Iterator<Integer> it1;
    Iterator<Integer> it2;
    for (it1 = this.iterator(), it2 = other.iterator(); it1.hasNext(); ) {
      Integer x = it1.next();
      Integer y = it2.next();
      if (x.equals(y)) {
        continue;
      }
      if (x.compareTo(y) > 0) {
        return sign1 == 1 ? 1 : -1;
      } else {
        return sign1 == 0 ? 1 : -1;
      }
    }
    return 0;
  }

  /** returns the number of digits in this number. */
  @Override
  public int length() {
    return size;
  }

  /**
   * A function that takes the number of shifts as an argument and shifts this number to the left by
   * that number. A negative number of left-shifts will correspond to those many right shifts.
   */
  @Override
  public void shiftLeft(int numberOfShifts) {
    if (length() == 1 && head.data == 0) {
      return;
    }
    if (numberOfShifts >= 0) {
      while (numberOfShifts > 0) {
        addLast(0);
        numberOfShifts--;
      }
    } else {
      shiftRight(Math.abs(numberOfShifts));
    }
  }

  /**
   * Function that takes the number of shifts as an argument and shifts this number to the right by
   * that number. A negative number of right-shifts will correspond to those many left shifts.
   */
  @Override
  public void shiftRight(int numberOfShifts) {
    if (numberOfShifts >= length()) {
      linkedList();
      addLast(0);
      return;
    }
    if (numberOfShifts > 0) {
      while (numberOfShifts > 0) {
        removeLast();
        numberOfShifts--;
      }
    } else {
      shiftLeft(Math.abs(numberOfShifts));
    }
  }

  /**
   * Function that takes a single digit as an argument and adds it to this number. This method
   * throws an IllegalArgumentException if the digit passed to it is not a single non-negative digit
   */
  @Override
  public void addDigit(int digit) {
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException("the digit is not a single non-negative digit!");
    }
    Node cur = this.tail;
    int sum = digit + cur.data;
    cur.data = sum % 10;
    int carry = sum / 10;
    while (cur.prev != null && carry > 0) {
      sum = cur.prev.data + carry;
      cur.prev.data = sum % 10;
      carry = sum / 10;
      cur = cur.prev;
    }
    if (carry > 0) {
      addFirst(carry);
    }
  }

  /**
   * Method that takes a position as an argument and returns the digit at that position. This method
   * throws an IllegalArgumentException if an invalid position is passed.
   */
  @Override
  public int getDigitAt(int position) {
    if (position < 0) {
      throw new IllegalArgumentException("index out of bounds");
    } else if (position >= size) {
      return 0;
    }
    return valueAtIndex(size - position - 1);
  }

  /** returns an identical and independent copy of this number. */
  @Override
  public BigNumber copy() {
    BigNumberImpl newNumber = new BigNumberImpl();
    if (isEmpty()) {
      return newNumber;
    }
    Node copyHead = head;
    newNumber.removeLast();
    while (copyHead != null) {
      newNumber.addLast(copyHead.data);
      copyHead = copyHead.next;
    }
    newNumber.isPositive = isPositive;
    return newNumber;
  }

  /**
   * A method add that takes another BigNumber and returns the sum of these two numbers. This
   * operation does not change either number
   */
  @Override
  public BigNumber add(BigNumber other) {
    BigNumberImpl temp = new BigNumberImpl();
    temp.removeFirst();
    BigNumberImpl other2 = (BigNumberImpl) other.copy();
    Iterator<Integer> itBig;
    Iterator<Integer> itSmall;
    if (size > other2.size) {
      itBig = this.reversedIterator();
      itSmall = other2.reversedIterator();
    } else {
      itBig = other2.reversedIterator();
      itSmall = this.reversedIterator();
    }
    int carry = 0;
    while (itSmall.hasNext()) {
      int i = carry + itSmall.next() + itBig.next();
      carry = i / 10;
      temp.addFirst(i % 10);
    }
    while (itBig.hasNext()) {
      int i = carry + itBig.next();
      carry = i / 10;
      temp.addFirst(i % 10);
    }
    if (carry > 0) {
      temp.addFirst(carry);
    }
    return temp;
  }

  /** method to compare and check if two BigNumber objects are equal. */
  @Override
  public boolean isEqual(BigNumber bigNumber) {
    return this.compareTo(bigNumber) == 0;
  }

  private class LinkedIterator implements Iterator {
    private Node current;

    public LinkedIterator(Node head) {
      current = head;
    }

    public boolean hasNext() {
      return current != null;
    }

    public Integer next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      int item = current.data;
      current = current.next;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("Remove is not supported by this iterator.");
    }
  }

  private class ReversedLinkedIterator implements Iterator {
    private Node current;

    public ReversedLinkedIterator(Node tail) {
      current = tail;
    }

    public boolean hasNext() {
      return current != null;
    }

    public Integer next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      int item = current.data;
      current = current.prev;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("Remove is not supported by this iterator.");
    }
  }
}
