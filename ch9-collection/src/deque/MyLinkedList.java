package deque;

import java.util.*;

public class MyLinkedList<E>
{
  private int size = 0;

  private Node<E> first;

  private Node<E> last;

  public MyLinkedList() {}

  public MyLinkedList(Collection<? extends E> c) {
    this();
//    addAll(c);
  }

  // Basic list operations

  private void linkFirst(E e) {
    final Node<E> f = first;
    final Node<E> newNode = new Node<>(null, e, f);
    first = newNode;
    if (f == null)
      last = newNode;
    else
      f.prev = newNode;
    size++;
//    modCount++;
  }

  void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
      first = newNode;
    else
      l.next = newNode;
    size++;
//    modCount++;
  }

  void linkBefore(E e, Node<E> succ) {
    final Node<E> prev = succ.prev;
    final Node<E> newNode = new Node<>(prev, e, succ);
    succ.prev = newNode;
    if (prev == null)
      first = newNode;
    else
      prev.next = newNode;
    size++;
  }

  private E unlinkFirst(Node<E> f) {
    final E item = f.item;
    final Node<E> next = f.next;
    f.item = null;
    f.next = null;
    first = next;
    if (next == null)
      last = null;
    else
      next.prev = null;
    size--;
    return item;
  }

  private E unlinkLast(Node<E> l) {
    final E item = l.item;
    final Node<E> prev = l.prev;
    l.item = null;
    l.prev = null;
    last = prev;
    if (prev == null)
      first = null;
    else
      prev.next = null;
    size--;
    return item;
  }

  E unlink(Node<E> x) {
    final E item = x.item;
    final Node<E> prev = x.prev;
    final Node<E> next = x.next;
    x.item = null;

    if (prev == null)
      first = next;
    else {
      prev.next = next;
      x.prev = null;
    }

    if (next == null)
      last = prev;
    else {
      next.prev = prev;
      x.next = null;
    }
    size--;
    return item;
  }

  public E getFirst() {
    if (first == null)
      throw new NoSuchElementException();
    return first.item;
  }

  public E getLast() {
    if (last == null)
      throw new NoSuchElementException();
    return last.item;
  }

  public E removeFirst() {
    if (first == null)
      throw new NoSuchElementException();
    return unlinkFirst(first);
  }

  public E removeLast() {
    if (last == null)
      throw new NoSuchElementException();
    return unlinkLast(last);
  }

  public void addFirst(E e) {
    linkFirst(e);
  }

  public void addLast(E e) {
    linkLast(e);
  }

  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  public int size() {
    return size;
  }

  public boolean add(E e) {
    linkLast(e);
    return true;
  }

  public boolean remove(Object o) {
    if (o == null) {
      for (Node<E> x = first; x != null; x = x.next) {
        if (x == null)
          unlink(x);
        return true;
      }
    } else {
      for (Node<E> x = first; x != null; x = x.next) {
        if (x.equals(o))
          unlink(x);
        return true;
      }
    }
    return false;
  }

  public boolean addAll(Collection<? extends E> c) {
    return addAll(size, c);
  }

  public boolean addAll(int index, Collection<? extends E> c) {
    checkPositionIndex(index);

    Object[] a = c.toArray();
    int newNum = a.length;
    if (newNum == 0)
      return false;

    Node<E> pred, succ;
    if (index == size) {
      succ = null;
      pred = last;
    } else {
      succ = node(index);
      pred = succ.prev;
    }

    for (Object o : a) {
      E e = (E) o;
      Node<E> newNode = new Node<>(pred, e, null);
      if (pred == null)
        first = newNode;
      else
        pred.next = newNode;
      pred = newNode;
    }

    if (succ == null)
      last = null;
    else {
      succ.prev = pred;
      pred.next = succ;
    }

    size++;
    return true;
  }

  public void clear() {
    for (Node<E> x = first; x != null;) {
      Node<E> next = x.next;
      x.item = null;
      x.prev = null;
      x.next = null;
      x = next;
    }

    first = last = null;
    size = 0;
  }

  // Positional Access Operations

  public E get(int index) {
    checkElementPosition(index);
    return node(index).item;
  }

  public E set(int index, E element) {
    checkPositionIndex(index);
    Node<E> x = node(index);
    E oldVal = x.item;
    x.item = element;
    return oldVal;
  }

  public void add(int index, E element) {
    checkPositionIndex(index);

    if (index == size)
      linkLast(element);
    else
      linkBefore(element, node(index));
  }

  public E remove(int index) {
    checkElementPosition(index);
    return unlink(node(index));
  }

  private boolean isElementPosition(int index) {
    return index >= 0 && index < size;
  }

  private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;
  }

  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }

  private void checkElementPosition(int index) {
    if (!isElementPosition(index))
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
  }

  private void checkPositionIndex(int index) {
    if (!isPositionIndex(index))
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
  }

  Node<E> node(int index) {
    if (index < (size >> 1)) {
      Node<E> x = first;
      for (int i = 0; i < index; i++)
        x = x.next;
      return x;
    } else {
      Node<E> x = last;
      for (int i = size - 1; i > index; i--)
        x = x.prev;
      return x;
    }
  }


  // Search Operations

  public int indexOf(Object o) {
    int index = 0;
    if (o == null) {
      for (Node<E> x = first; x != null; x = x.next) {
        if (x.item == null)
          return index;
        index++;
      }
    } else {
      for (Node<E> x = first; x != null; x = x.next) {
        if (o.equals(x.item))
          return index;
        index++;
      }
    }
    return -1;
  }

  public int lastIndexOf(Object o) {
    int index = size - 1;
    if (o == null) {
      for (Node<E> x = last; x != null; x = x.prev) {
        if (x.item == null)
          return index;
        index--;
      }
    } else {
      for (Node<E> x = last; x != null; x = x.prev) {
        if (o.equals(x.item))
          return index;
        index--;
      }
    }
    return -1;
  }

  // Queue Operations

  public E peek() {
      return (first == null) ? null : first.item;
  }

  public E element() {
    return getFirst();
  }

  public E poll() {
    return first == null ? null : unlinkFirst(first);
  }

  public E remove() {
    return removeFirst();
  }

  public boolean offer(E e) {
    return add(e);
  }

  // Dequeu operations
  public boolean offerFirst(E e) {
    addFirst(e);
    return true;
  }

  public boolean offerLast(E e) {
    addLast(e);
    return true;
  }

  public E peekFirst() {
    return (first == null) ? null : first.item;
  }

  public E peekLast() {
    return (last == null) ? null : last.item;
  }

  public E pollFirst() {
    return (first == null) ? null : unlinkFirst(first);
  }

  public E pollLast() {
    return (last == null) ? null : unlinkLast(last);
  }

  public void push(E e) {
    addFirst(e);
  }

  public E pop() {
    return removeFirst();
  }

  public boolean removeFirstOccurence(Object o) {
    return remove(o);
  }

  private static class Node<E> {
    E item;
    Node<E> prev;
    Node<E> next;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }

  public Object[] toArray() {
    Object[] result = new Object[size];
    int i = 0;
    for (Node<E> x = first; x != null; x = x.next)
      result[i++] = x.item;
    return result;
  }



}
