package arraylist;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ArrayListTest {
  public static void main(String[] args) {
//    CMExceptionTest();
//    addTest();
//    addAllTest();
//    addAllTest2();
//    removeAllTest();
//    hashCodeTest();
    equalsTest();
  }
  public static void CMExceptionTest() {
    ArrayList<Integer> ages = new ArrayList<>();
    // add some elements to the ArrayList
    ages.add(10);
    ages.add(12);
    ages.add(15);
    ages.add(19);
    ages.add(23);
    ages.add(34);
    for (Integer i: ages) {
      ages.add(10);
    }
  }

  public static void addTest() {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++)
      list.add(i);
    list.add(10);
  }

  public static void addAllTest() {
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    list2.add(1);
    list2.add(2);
    list2.add(3);
    list1.addAll(list2);
  }

  public static void addAllTest2() {
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    for (int i = 1; i <= 3; i++) {
      list1.add(i);
      list2.add(i);
    }

    list1.addAll(1, list2);
    System.out.println(list1);
  }

  public static void removeAllTest() {
    List<Integer> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>();

    l1.add(1);
    l1.add(2);
    l1.add(3);
    l1.add(3);
    l1.add(4);
    l1.add(4);
    l1.add(4);
    l1.add(5);
    l1.add(6);
    System.out.println("l1: " + l1);

    l2.add(2);
    l2.add(4);
    System.out.println("l1 removeAll l2: " + l2);
    l1.removeAll(l2);
    System.out.println("l1: " + l1);
  }

  public static void hashCodeTest() {
//    List<Integer> l = new ArrayList<>();
//    for (int i = 0; i < 6; i++)
//      l.add(0);
//    System.out.println(l.hashCode());
//    System.out.println(Math.pow(31.0, 6));

//    Object o1 = 1000;
//    Object o2 = new Object();
//    Object o3 = new Object();
//    System.out.println(o1.hashCode());
//    System.out.println(o2.hashCode());
//    System.out.println(o3.hashCode());

//    String s1 = "1000";
//    String s2 = new String("1000");
//    String s3 = new String("2000");
//    System.out.println("s1 hash: " + s1.hashCode());
//    System.out.println("s2 hash: " + s2.hashCode());
//    System.out.println("s3 hash: " + s3.hashCode());

//    Integer i1 = 1000;
//    Integer i2 = new Integer(1000);
//    Integer i3 = new Integer(2000);
//    System.out.println("i1 hash: " + i1.hashCode());
//    System.out.println("i2 hash: " + i2.hashCode());
//    System.out.println("i3 hash: " + i3.hashCode());

  }

  public static void equalsTest() {
    List<Object> l1 = new ArrayList<>();
    List<Object> l2 = new ArrayList<>();
//    l1.add(new Object());
//    l2.add(null);
//    System.out.println(l1.equals(l2));

//    l1.add(1);
//    l2.add("1");
//    System.out.println(l1.equals(l2));

//    l1.add(1);
//    l2.add(1.0);
//    System.out.println(l1.equals(l2));

    class Person {
      String name;
      Integer id;
      Person(String name, Integer id) {
        this.name = name;
        this.id = id;
      }
      public int hashCode() {
        return Objects.hash(name, id);
      }
      public boolean equals(Object other) {
        if (this == other)
          return true;
        if (!(other instanceof Person))
          return false;
        Person p = (Person) other;
        return Objects.equals(this.id, p.id) && Objects.equals(this.name, p.name);
      }
    }
    Person p1 = new Person(new String("100"), new Integer(100));
    Person p2 = new Person("100", 100);
//    boolean b = p1.equals(p2);
//    Set<Person> set = new HashSet<>();
//    set.add(p1);
//    boolean b2 = set.contains(p2);
    l1.add(p1);
    l2.add(p2);
    boolean b3 = l1.equals(l2); // 在此处断点跟踪调试 会逐级比较对象的每个属性
  }
}
