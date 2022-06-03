public class PercentageDiscountPricing implements ISalePricing {
  private double percentage;

  public PercentageDiscountPricing(double percentage) {
    if (!(percentage >= 0 && percentage <= 100))
      throw new IllegalArgumentException("Expected percentage to be between 0 and 100 (inclusive).");

    this.percentage = percentage;
  }

  public long getTotal(Sale sale) {
    return (long) (((100 - percentage) / 100) * sale.getPreDiscountTotal());
  }

  public static void main(String[] args) {
    PercentageDiscountPricing p = new PercentageDiscountPricing(-1.0);
  }
}