package deque;

import java.util.LinkedList;

public class LinkedListTest {
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.addFirst(5);
    list.addLast(6);
    print(list);

    list.removeFirst();
    list.removeLast();
    print(list);

    list.poll();
    list.poll();
    list.poll();
    list.poll();
    print(list);

  }

  public static <E> void print(LinkedList<E> list) {
    for (E i : list)
      System.out.print(i + " ");
    System.out.println();
  }
}
