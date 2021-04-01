package bank;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchBank {
  private final double[] accounts;
  private Lock bankLock;
  private Condition sufficientFunds;

  public SynchBank(int n, double initialBalance) {
    accounts = new double[n];
    Arrays.fill(accounts, initialBalance);
    bankLock = new ReentrantLock();
    sufficientFunds = bankLock.newCondition();
  }

  public void transfer(int from, int to, double amount) throws InterruptedException {
    bankLock.lock();
    try {
      while (accounts[from] < amount)
        sufficientFunds.await();
      System.out.print(Thread.currentThread());
      accounts[from] -= amount;
      accounts[to] += amount;
      System.out.println("Total balance: " + getTotalBalance());
      sufficientFunds.signalAll();
    } finally {
      bankLock.unlock();
    }

  }

  public double getTotalBalance() {
    bankLock.lock();
    try {
      double sum = 0;
      for (double i : accounts)
        sum += i;
      return sum;
    } finally {
      bankLock.unlock();
    }

  }

  public int size() {
    return accounts.length;
  }
}
