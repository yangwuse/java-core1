package vector;

import arraylist.ArrayList;

import java.util.List;
import java.util.Vector;

public class MyVectorTest {
  public static void main(String[] args) {
//    constructor();
//    addTest();
    removeTest();
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

  public static void removeTest() {
    MyVector<Integer> v = new MyVector<>();
    v.add(1);
    v.add(2);
    v.add(3);
    v.remove(1);

  }


}
