package bank;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchBankTwo {
  private final double[] accounts;

  public SynchBankTwo(int n, double initialBalance) {
    accounts = new double[n];
    Arrays.fill(accounts, initialBalance);
  }

  public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
    while (accounts[from] < amount)
      wait();
    System.out.print(Thread.currentThread());
    accounts[from] -= amount;
    accounts[to] += amount;
    System.out.println("Total balance: " + getTotalBalance());
    notify();
  }

  public synchronized double getTotalBalance() {
    double sum = 0;
    for (double i : accounts)
      sum += i;
    return sum;
  }

  public int size() {
    return accounts.length;
  }
}
