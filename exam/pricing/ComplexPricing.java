import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ComplexPricing implements ISalePricing {
  private List<ISalePricing> pricings = new ArrayList<ISalePricing>();

  public ComplexPricing(ISalePricing pricing) {
    Objects.requireNonNull(pricing, "pricing must not be null");
    pricings.add(pricing);
  }

  public void add(ISalePricing pricing) {
    Objects.requireNonNull(pricing, "pricing must be non null");
    pricings.add(pricing);
  }

  public List<ISalePricing> getPricings() {
    return pricings;
  }
}
