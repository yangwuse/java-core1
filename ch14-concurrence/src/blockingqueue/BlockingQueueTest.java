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
  private static int totalLine = 0;
  private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

  public static void main(String[] args) throws InterruptedException {
    try (Scanner in = new Scanner(System.in)) {
      System.out.print("Enter base directory: "); // /Users/yangwu/jdkcompile/openjdk11/src
      String directory = in.nextLine();
      System.out.print("Enter keyword: "); // synchronized
      String keyword = in.nextLine();

      Runnable enumerator = () -> {
        try {
          enumerate(new File(directory));
          queue.put(DUMMY);
        } catch (InterruptedException e) {}
      };
      new Thread(enumerator).start();

      for (int i = 1; i <= SEARCH_THREADS; i++) {
        Runnable searcher = () -> {
          try {
            boolean done = false;
            while (!done) {
              File file = queue.take();
              if (file == DUMMY) {
                queue.put(file);
                done = true;
              } else search(file, keyword);
            }
          } catch (IOException e) {}
            catch (InterruptedException e) {}
        };
        new Thread(searcher).start();
      }
    }
    Thread.sleep(20*1000);
    System.out.println(totalLine);
  }

  public static void enumerate(File directory) throws InterruptedException {
    File[] files = directory.listFiles();
    for (File file : files)
      if (file.isDirectory()) enumerate(file);
      else queue.put(file);
  }

  public static synchronized void search(File file, String keyword) throws FileNotFoundException {
    try (Scanner in = new Scanner(file, "UTF-8")) {
      int lineNumer = 0;
      while (in.hasNextLine()) {
        lineNumer++;
        String line = in.nextLine();
        if (line.contains(keyword)) {
          System.out.printf("%s:%d:%s%n", file.getPath(), lineNumer, line);
          totalLine++;
        }
      }
    }
  }

}
