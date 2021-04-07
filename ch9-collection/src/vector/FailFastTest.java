package vector;

import java.util.Iterator;

public class FailFastTest {
  static Vector<Integer> v = new Vector<>();

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++)
      v.add(i);

    thread1();
    thread2();
  }

  public static void thread1() {

    Runnable r = () -> {
      Iterator<Integer> it = v.iterator();
      while (it.hasNext()) {
        it.next();
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    new Thread(r).start();
  }

  public static void thread2() {
    Runnable r = () -> {
      for (Integer i : v) {
        if (i == 3) v.remove(i);
        System.out.print(i + " ");
      }
      System.out.println();
    };
    new Thread(r).start();
  }
}
