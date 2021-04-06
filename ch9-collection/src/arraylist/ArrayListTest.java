package arraylist;

public class ArrayListTest {
  public static void main(String[] args) {
//    CMExceptionTest();
//    addTest();
    addAllTest();
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

}
