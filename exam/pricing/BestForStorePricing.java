import java.util.List;

public class BestForStorePricing extends ComplexPricing {
  public BestForStorePricing(ISalePricing pricing) {
    super(pricing);
  }

  @Override
  public long getTotal(Sale sale) {
    List<ISalePricing> pricings = getPricings();
    long maximumTotal = Long.MIN_VALUE;

    if (pricings.isEmpty())
      return sale.getTotal();

    for (ISalePricing pricing : pricings) {
      long currentTotal = pricing.getTotal(sale);
      if (currentTotal > maximumTotal)
        maximumTotal = currentTotal;
    }

    return maximumTotal;
  }
}
