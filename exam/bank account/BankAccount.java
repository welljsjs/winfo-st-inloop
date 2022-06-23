// Copyright 2022 Julius Schmidt
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import java.util.Objects;

public class BankAccount {
  private AccountState state;
  private double balance;
  private double lineOfCredit;
  private String accountNumber;
  private final AccountState positiveAccountState = new Positive();
  private final AccountState negativeAccountState = new Negative();
  private final AccountState frozenAccountState = new Frozen();
  private final AccountState closedAccountState = new Closed();

  public BankAccount(String accountNumber, double lineOfCredit) {
    Objects.requireNonNull(accountNumber, "accountNumber must be non-null");
    if (accountNumber.isEmpty())
      throw new IllegalArgumentException("accountNumber must be non-empty");
    if (lineOfCredit < 0)
      throw new IllegalArgumentException("lineOfCredit must be non-negative");

    this.accountNumber = accountNumber;
    this.lineOfCredit = lineOfCredit;
    this.state = positiveAccountState;
  }

  public boolean payIn(double amount) {
    return state.payIn(amount);
  }

  public boolean payOff(double amount) {
    return state.payOff(amount);
  }

  public boolean close() {
    if (balance != 0)
      return false;
    state = closedAccountState;
    return true;
  }

  public double getBalance() {
    return balance;
  }

  public String getState() {
    return state.toString();
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void printBalance() {
    state.printBalance();
  }

  public void payInterest() {
    state.payInterest();
  }

  public abstract class AccountState {
    public boolean payIn(double amount) {
      if (amount <= 0)
        throw new IllegalArgumentException("amount must be positive");
      return false;
    }

    public boolean payOff(double amount) {
      if (amount <= 0)
        throw new IllegalArgumentException("amount must be positive");
      return false;
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }

    public void payInterest() {
    }

    public abstract void printBalance();
  }

  public class Positive extends AccountState {
    final static double rate = 0.01;

    @Override
    public boolean payIn(double amount) {
      if (amount <= 0)
        throw new IllegalArgumentException("amount must be positive");
      balance += amount;
      return true;
    }

    @Override
    public boolean payOff(double amount) {
      if (amount <= 0)
        throw new IllegalArgumentException("amount must be positive");
      if (balance - amount < -lineOfCredit)
        return false;
      balance -= amount;
      if (balance == -lineOfCredit)
        state = frozenAccountState;
      else if (balance < 0)
        state = negativeAccountState;
      return true;
    }

    @Override
    public void payInterest() {
      balance += (balance * rate);
    }

    @Override
    public void printBalance() {
      System.out.println("Balance is POSITIVE: +" + balance + ".");
    }

  }

  public class Negative extends AccountState {
    final static double rate = 0.03;

    @Override
    public boolean payIn(double amount) {
      if (amount <= 0)
        throw new IllegalArgumentException("amount must be positive");
      balance += amount;
      if (balance >= 0)
        state = positiveAccountState;
      return true;
    }

    @Override
    public boolean payOff(double amount) {
      if (amount <= 0)
        throw new IllegalArgumentException("amount must be positive");
      if (balance - amount < -lineOfCredit)
        return false;
      balance -= amount;
      if (balance == -lineOfCredit)
        state = frozenAccountState;
      return true;
    }

    @Override
    public void payInterest() {
      balance *= (1d + rate);
      if (balance <= -lineOfCredit)
        state = frozenAccountState;
    }

    @Override
    public void printBalance() {
      System.out.println("Balance is NEGATIVE: " + balance + ".");
    }

  }

  public class Frozen extends AccountState {
    final static double rate = 0.05;

    @Override
    public boolean payIn(double amount) {
      if (amount <= 0)
        throw new IllegalArgumentException("amount must be positive");
      balance += amount;
      state = (balance < 0) ? negativeAccountState : positiveAccountState;
      return true;
    }

    @Override
    public void payInterest() {
      balance *= (1d + rate);
    }

    @Override
    public void printBalance() {
      System.out.println("Balance is NEGATIVE: " + balance + ". You need to pay in money.");
    }

  }

  public class Closed extends AccountState {

    @Override
    public void printBalance() {
      System.out.println("This account is CLOSED. The balance is " + Math.round(balance) + ".");
    }

    @Override
    public void payInterest() {
      throw new IllegalStateException("Cannot pay interest when in closed state");
    }

  }
}
