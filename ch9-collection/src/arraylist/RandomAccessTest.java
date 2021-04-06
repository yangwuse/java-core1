package arraylist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

public class RandomAccessTest {
  public static long randomAccess(List list) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < list.size(); i++)
      list.get(i);
    long end = System.currentTimeMillis();
    return end - start;
  }

  public static long iteratorAccess(List list) {
    Iterator iterator = list.iterator();
    long start = System.currentTimeMillis();
    while (iterator.hasNext()) {
      iterator.next();
    }
    long end = System.currentTimeMillis();
    return end - start;
  }

  public static void main(String[] args) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new LinkedList<>();

    for (int i = 0; i< 200000; i++) {
      list1.add(i);
      list2.add(i);
    }

    long time1 = randomAccess(list1);
    long time2 = randomAccess(list2);

    long time3 = iteratorAccess(list1);
    long time4 = iteratorAccess(list2);

    System.out.println("list1 randomAccess: " + time1);
    System.out.println("list1 iteratorAccess: " + time3);
    System.out.println("list2 randomAccess: " + time2);
    System.out.println("list2 iteratorAccess: " + time4);

  }
}
