package hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
  public static void main(String[] args) {
//    nullTest();
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


}
