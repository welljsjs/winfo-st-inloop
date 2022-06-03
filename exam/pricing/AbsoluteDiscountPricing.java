class AbsoluteDiscountPricing implements ISalePricing {
  private long discount;
  private long threshold;

  public AbsoluteDiscountPricing(long discount, long threshold) {
    if (discount < 0 || threshold < 0)
      throw new IllegalArgumentException("Expected discount and threshold to be non-negative");

    this.discount = discount;
    this.threshold = threshold;
  }

  public long getTotal(Sale sale) {
    long preDiscountTotal = sale.getPreDiscountTotal();
    long postDiscountTotal = preDiscountTotal - discount;
    long total = (postDiscountTotal >= threshold) ? postDiscountTotal : Math.min(threshold, preDiscountTotal);
    System.err.println("preDiscountTotal: " + preDiscountTotal + ", postDiscountTotal: " + postDiscountTotal
        + ", discount: " + discount + ", threshold: " + threshold + ", total: " + total + "\n");
    return total;
  }
}