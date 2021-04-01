package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("/Users/yangwu/IdeaProjects/java-core1/ch14-concurrence/src/bank/Bank.java");
    try (Scanner in = new Scanner(file, "UTF-8")) {
      while (in.hasNextLine()) {
        String line = in.nextLine();
        System.out.println(line);
      }
    }
  }
}
