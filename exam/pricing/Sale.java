import java.util.Objects;

public class Sale {
  private long preDiscountTotal;
  private ISalePricing pricing;

  /**
   * 
   * @param preDiscountTotal The catalog price of the product in cents before any
   *                         discounts have been applied.
   * @param pricing
   */
  public Sale(long preDiscountTotal, ISalePricing pricing) {
    Objects.requireNonNull(pricing, "pricing must be non null");
    if (preDiscountTotal < 0)
      throw new IllegalArgumentException("Expected preDiscountTotal to be non-negative");

    this.preDiscountTotal = preDiscountTotal;
    this.pricing = pricing;
  }

  public long getPreDiscountTotal() {
    return preDiscountTotal;
  }

  public void setPricing(ISalePricing pricing) {
    Objects.requireNonNull(pricing, "pricing must be non null");
    this.pricing = pricing;
  }

  public long getTotal() {
    return pricing.getTotal(this);
  }

  public static ISalePricing createPricing(DiscountType discountType,
      double percentage, long discount, long threshold) {
    Objects.requireNonNull(discountType, "discountType must be non null");

    ISalePricing pricing = null;

    if (discountType == DiscountType.ABSOLUTEDISCOUNT) {
      if (percentage != 0)
        throw new IllegalArgumentException("Expected percentage to be null for absolute discount");
      pricing = new AbsoluteDiscountPricing(discount, threshold);
    } else if (discountType == DiscountType.PERCENTAGEDISCOUNT) {
      if (discount != 0 || threshold != 0)
        throw new IllegalArgumentException("Expected discount and threshold to be null for percentage discount");
      pricing = new PercentageDiscountPricing(percentage);
    }
    return pricing;
  }
}
