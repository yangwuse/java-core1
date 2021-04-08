package vector;

import arraylist.ArrayList;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class MyVectorTest {
  public static void main(String[] args) {
//    constructor();
//    addTest();
//    removeByIndexTest();
//    removeByEleTest();
    synchronizedTest();
  }

  public static void constructor() {
    MyVector<Integer> v1 = new MyVector<>(20, 10);
    MyVector<Integer> v2 = new MyVector<>(30);
    MyVector<Integer> v3 = new MyVector<>();

    List<Integer> l = new ArrayList<>();
    l.add(1);
    l.add(2);
    MyVector<Integer> v4 = new MyVector<>(l);
  }

  public static void addTest() {
    MyVector<Integer> v = new MyVector<>(200);
    // 2亿个整数没问题 800MB
    for (int i = 0; i < 200; i++)
      v.add(i);
    System.out.println();
  }

  public static void removeByIndexTest() {
    MyVector<Integer> v = new MyVector<>();
    v.add(1);
    v.add(2);
    v.add(3);
    v.remove(1);
  }

  public static void removeByEleTest() {
    MyVector<String> v = new MyVector<>();
    v.add("1111");
    v.add("2222");
    v.add("3333");
    v.add("4444");

    v.remove("3333");
    System.out.println(v);
  }

  public static void synchronizedTest() {
    int num = 10000;
    Vector<Integer> v = new Vector<>(num);
    for (int i = 0; i < num; i++)
      v.add(i);

    for (int i = 0; i < 10; i++) {
      Runnable r = () -> {
        synchronized (v) { // 对v加锁 或者用可重入锁 或者对方法加锁
          for (int j = 0; j < v.size(); j++)
            v.remove(j);
        }
      };
      new Thread(r).start();
    }
  }
}
