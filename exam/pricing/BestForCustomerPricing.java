import java.util.List;

public class BestForCustomerPricing extends ComplexPricing {
  public BestForCustomerPricing(ISalePricing pricing) {
    super(pricing);
  }

  @Override
  public long getTotal(Sale sale) {
    List<ISalePricing> pricings = getPricings();
    long minimumTotal = Long.MAX_VALUE;

    if (pricings.isEmpty())
      return sale.getTotal();

    for (ISalePricing pricing : pricings) {
      long currentTotal = pricing.getTotal(sale);
      if (currentTotal < minimumTotal)
        minimumTotal = currentTotal;
    }

    return minimumTotal;
  }
}