package bank;

import java.util.Arrays;

public class Bank {
  private final double[] accounts;

  public Bank(int n, double initialBalance) {
    accounts = new double[n];
    Arrays.fill(accounts, initialBalance);
  }

  public void transfer(int from, int to, double amount) {
    if (accounts[from] < amount) return;
    System.out.print(Thread.currentThread());
    accounts[from] -= amount;
    accounts[to] += amount;
    System.out.println("Total balance: " + getTotalBalance());
  }

  public double getTotalBalance() {
    double sum = 0;
    for (double i : accounts)
      sum += i;
    return sum;
  }

  public int size() {
    return accounts.length;
  }
}
