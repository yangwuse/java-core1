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

}
