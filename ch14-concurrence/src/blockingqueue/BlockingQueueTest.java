package blockingqueue;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
  private static final int FILE_QUEUE_SIZE = 10;
  private static final int SEARCH_THREADS = 100;
  private static final File DUMMY = new File("");
  private static int totalCnt = 0;
  private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

  public static void main(String[] args) throws InterruptedException {
    try (Scanner in = new Scanner(System.in)) {
      System.out.print("Enter base dir (eg /User/yangwu): ");
      String dir = in.nextLine();
      System.out.print("Enter keyword (eg class): ");
      String keyword = in.nextLine();

      Runnable enumerator = () -> {
        try {
          enumerate(new File(dir));
          queue.put(DUMMY);
        } catch (Exception e) {}
      };
      new Thread(enumerator).start();

      for (int i = 1; i <= SEARCH_THREADS; i++) {
        Runnable searcher = () -> {
          try {
            boolean done = false;
            while (!done) {
              File file = queue.take();
              if (file == DUMMY) {
                done = true;
                queue.put(file);
              } else search(file, keyword);
            }
          } catch (Exception e) {}
        };
        new Thread(searcher).start();
      }
    }
    Thread.sleep(15 * 1000);
    System.out.println(totalCnt);
  }

  public static void enumerate(File dir) throws InterruptedException {
    File[] files = dir.listFiles();
    for (File file : files)
      if (file.isDirectory()) enumerate(file);
      else queue.put(file);
  }

  public static synchronized void search(File file, String keyword) throws FileNotFoundException {
    try (Scanner in = new Scanner(file, "utf-8")) {
      int lineNum = 0;
      while (in.hasNextLine()) {
        lineNum++;
        String line = in.nextLine();
        if (line.contains(keyword)) {
          System.out.printf("%s:%d:%s%n", file.getPath(), lineNum, line);
          totalCnt++;
        }
      }
    }
  }

}
