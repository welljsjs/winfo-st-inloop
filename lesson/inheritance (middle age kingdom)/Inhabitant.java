public class Inhabitant {
  protected int income;

  public int taxableIncome() {
    return income;
  }

  public int tax() {
    return Math.max((int) (0.1 * taxableIncome()), 1);
  }

  public void setIncome(int income) {
    if (income < 0)
      throw new IllegalArgumentException("income must be non-negative");
    this.income = income;
  }
}