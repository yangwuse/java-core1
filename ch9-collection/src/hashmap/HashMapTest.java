package hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
  public static void main(String[] args) {
//    nullTest();
    synTest();
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

}