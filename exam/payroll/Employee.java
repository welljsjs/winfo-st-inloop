import java.util.Objects;

public abstract class Employee {
  private String id;

  public Employee(String id) {
    Objects.requireNonNull(id, "id must be non-null");
    if (id.isEmpty())
      throw new IllegalArgumentException("id must be non-empty");

    this.id = id;
  }

  public String getId() {
    return id;
  }

  public abstract boolean isPayday(int dayOfMonth);

  public abstract double calculatePay() throws UnpayableEmployeeException;

  public abstract double calculateDeductions();
}
