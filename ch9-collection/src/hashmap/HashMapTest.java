package hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapTest {
  public static void main(String[] args) {
//    nullTest();
//    synTest();
//    capacityTest();
//    hashTest();
//    leadingZeroTest();
//    getTest();
    traverseTest();
  }

  public static void nullTest() {
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "11111");
    map.put(null, "22222");
    map.put(2, null);
    // override map.put(null, "22222")
    map.put(null, null);

    System.out.println(map.get(1));
    System.out.println(map.get(null));
    System.out.println(map.get(2));
  }

  public static void synTest() {
    int cap = 100;
    Map<Integer, Integer> map = new HashMap<>(cap);
    for (int i = 0; i < 3 * cap; i++)
      map.put(i, 2*i);

    Runnable r1 = () -> {
      try {
        while (true) {
          int key = (int)(Math.random()*3*cap);
          map.remove(key);
          System.out.println(Thread.currentThread() + " " + "remove: " + key);
          Thread.sleep(10);
        }
      } catch (InterruptedException e) {}

    };

    Runnable r2 = () -> {
      try {
        while (true) {
          int key = (int)(Math.random()*3*cap);
          int value = (int)(Math.random()*2*cap);
          map.put(key, value);
          System.out.println(Thread.currentThread()
              + " " + "put: " + "< " + key + " " + value + " >");
          Thread.sleep(10);
        }
      } catch (InterruptedException e) {}

    };

    Runnable r3 = () -> {
      try {
        while (true) {
          int key = (int)(Math.random()*3*cap);
          System.out.println(Thread.currentThread() + "get: " + key);
          int value = map.get(key);
          Thread.sleep(10);
        }
      } catch (InterruptedException e) { e.printStackTrace(); }
    };

    new Thread(r1).start();
    new Thread(r2).start();
    // 导致空指针异常
    new Thread(r3).start();

  }

  public static void capacityTest() {
    int total   = 3000000;
    // best cap = total / 0.75 = total * 4/3 = 40000000
    int bestCap = 4000000;
    int halfCap = 2000000;
    Map<Integer, Integer> map1 = new HashMap<>(bestCap);
    Map<Integer, Integer> map2 = new HashMap<>(halfCap);
    Map<Integer, Integer> map3 = new HashMap<>();

    Runnable r1 = () -> {
      long s1 = System.currentTimeMillis();
      for (int i = 0; i < total; i++)
        map1.put(i, i);
      long e1 = System.currentTimeMillis();
      System.out.println("初始化为最佳capcity: " + (e1 - s1) + "ms");
    };

    Runnable r2 = () -> {
      long s2 = System.currentTimeMillis();
      for (int i = 0; i < total; i++)
        map2.put(i, i);
      long e2 = System.currentTimeMillis();
      System.out.println("初始化一半capacity: " + (e2 - s2) + "ms");
    };

    Runnable r3 = () -> {
      long s3 = System.currentTimeMillis();
      for (int i = 0; i < total; i++)
        map3.put(i, i);
      long e3 = System.currentTimeMillis();
      System.out.println("未初始化capacity: " + (e3 - s3) + "ms");
    };

    new Thread(r1).start();
    new Thread(r2).start();
    new Thread(r3).start();

  }

  public static void hashTest() {
    int num = 30000000;
    // best init capacity = num / 0.75
    int initCap = 40000000;
    Map<Integer, Integer> map1 = new HashMap<>(initCap);
    Map<Integer, Integer> map2 = new HashMap<>(initCap);

    Runnable r1 = () -> {
      long s1 = System.currentTimeMillis();
      for (int i = 0; i < num; i++)
        map1.put(i, i);
      long e1 = System.currentTimeMillis();
      System.out.println("key都不相同：" + (e1 - s1) + "ms");
    };

    Runnable r2 = () -> {
      long s1 = System.currentTimeMillis();
      for (int i = 0; i < num; i++)
        map1.put(100, i);
      long e1 = System.currentTimeMillis();
      System.out.println("key都相同：" + (e1 - s1) + "ms");
    };

    new Thread(r1).start();
    new Thread(r2).start();
  }

  public static void leadingZeroTest() {
    int i1 = 0;
    int i2 = 0x7fffffff;
    int i3 = 0x80000000;
    int i4 = 0x000000ff;
    System.out.println(Integer.numberOfLeadingZeros(i1)); // 32
    System.out.println(Integer.numberOfLeadingZeros(i2)); // 1
    System.out.println(Integer.numberOfLeadingZeros(i3)); // 0
    System.out.println(Integer.numberOfLeadingZeros(i4)); // 24

    System.out.println(-1 >>> 29); // 7
  }

  public static void getTest() {
    Map<String, String> map = new HashMap<>();

  }

  public static void traverseTest() {
    Map<Integer, String> map = new HashMap<>(4);
    map.put(0, "");
    map.put(4, "");
    map.put(8, "");
    map.put(12, "");
    map.put(16, "");
    map.put(20, "");
    map.put(24, "");
    map.put(28, "");
    map.put(1, "");

    // 理想遍历顺序是：0->4->8->12->16->20->24->28->1
    // 实际遍历顺序和插入顺序没有固定关系 它是可变的
    // 遍历key set: 0->16->1->4->20->8->24->12->28
    System.out.println("foreach遍历结果：");
    for (Integer key : map.keySet()) {
      System.out.print(key + "->");
    }
    System.out.println();

    // 遍历 entry set
    for (Map.Entry entry : map.entrySet())
      System.out.println(entry.getKey() + ": " + entry.getValue());

    // foreach遍历通过结合迭代器实现
    System.out.println("迭代器实现遍历：");
    Iterator it = map.keySet().iterator();
    while (it.hasNext()) {
      Integer key = (Integer)it.next();
      System.out.print(key + "->");
    }
    System.out.println();

    it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next();
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
}
